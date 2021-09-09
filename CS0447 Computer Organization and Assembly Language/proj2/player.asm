.include "constants.asm"
.include "macros.asm"

# =================================================================================================
# Player
# =================================================================================================

.globl player_init
player_init:
enter
	# NOTE: this is unique to the player object. All other objects are made using
	# Object_new. it's just a special object.

	la t0, player
	# player.type = TYPE_PLAYER
	li t1, TYPE_PLAYER
	sw t1, Object_type(t0)

	# player.hw = PLAYER_HW, player.hh = PLAYER_HH
	li t1, PLAYER_HW
	sw t1, Object_hw(t0)
	li t1, PLAYER_HH
	sw t1, Object_hh(t0)

	# reset lives
	li t1, PLAYER_INIT_LIVES
	sw t1, player_lives

	# reset the rest
	jal player_respawn
leave

# ------------------------------------------------------------------------------
player_respawn:
enter
	la t0, player

	# player.x = player.y = 32.0
	li t1, 0x2000
	sw t1, Object_x(t0)
	sw t1, Object_y(t0)

	# player.vx = player.vy = 0
	sw zero, Object_vx(t0)
	sw zero, Object_vy(t0)

	# reset the other variables
	sw zero, player_iframes
	sw zero, player_fire_time
	sw zero, player_deadframes
	sw zero, player_angle
	sw zero, player_accel
	li t1, PLAYER_MAX_HEALTH
	sw t1, player_health
leave

# ------------------------------------------------------------------------------
.globl player_update
player_update:
enter   
	lw t0, player_deadframes
	beq t0, 0, branch
	sub t0, t0, 1
	sw t0, player_deadframes
	bne t0, 0, leave
	
	lw t0, player_lives
	ble t0, 0, lose
	li t0, PLAYER_RESPAWN_IFRAMES
	sw t0, player_iframes   
	 
	jal player_respawn
	j leave
lose:
	jal lose_game
	j leave
branch:
	lw t0, player_fire_time
	ble t0, 0, test
	sub t0, t0, 1
	sw t0, player_fire_time  
test:
	lw t0, player_iframes
	beq t0, 0, up
	sub t0, t0, 1
	sw t0, player_iframes  
up:
        jal player_check_input
        jal player_update_thrust
        
        la a0, player
        li a1, PLAYER_DRAG
        jal Object_damp_velocity
        
        jal Object_accumulate_velocity
        jal Object_wrap_position
leave:
leave
# ------------------------------------------------------------------------------
.globl player_draw
player_draw:
enter
	# don't draw the player if they're dead.
	lw   t0, player_deadframes
	bnez t0, _player_draw_return

	# if they're invulnerable, draw them 4 frames on, 4 frames off.
	lw   t0, player_iframes
	beqz t0, _player_draw_doit
	lw   t0, frame_counter
	and  t0, t0, 4
	beqz t0, _player_draw_return

	_player_draw_doit:
		# there are 16 different directions in the rotation animation.
		# this chooses which frame to use based on the player's angle (0 = up, 90 = right)
		# a1 = spr_player[((player_angle + 11) % 360) / 23]
		lw  t0, player_angle
		add t0, t0, 11
		blt t0, 360, _player_draw_a_nowrap
			sub t0, t0, 360
		_player_draw_a_nowrap:
		div t0, t0, 23
		sll t0, t0, 2
		la  a1, spr_player
		add a1, a1, t0
		lw  a1, (a1)
		jal Object_blit_5x5_trans

	_player_draw_return:
leave

# ------------------------------------------------------------------------------
.globl player_check_input
player_check_input:
enter
        jal input_get_keys			#v0 = input_get_keys();  
        
        lw t0, player_angle
        
        and t2, v0, KEY_L			#(v0 & KEY_L)
        beq t2, 0, not_l			#if((v0 & KEY_L) != 0)
        sub t0, t0, PLAYER_ANG_VEL	        #player_angle -= PLAYER_ANG_VEL
        
        bge t0, 0, store
        add t0, t0, 360
        sw t0, player_angle

not_l:
        and t3, v0, KEY_R
        beq t3, 0, not_r
        add t0, t0, PLAYER_ANG_VEL
        
        blt t0, 360, right_case
        sub t0, t0, 360
store:
        sw t0, player_angle
right_case:
	sw t0, player_angle
not_r:
        lw t1, player_accel
        and t5, v0, KEY_U
        beq t5, 0, not_up
        li t4, 1
        move t1, t4
        sw t1, player_accel
        j jump_b
not_up:
        li t4, 0
        move t1, t4
        sw t1, player_accel
jump_b:      
        and t6, v0, KEY_B
        beq t6, 0, exit
        jal player_fire 
exit:
leave
# ------------------------------------------------------------------------------
.globl player_fire
player_fire:
enter s0, s1, s2
        lw t0, player_fire_time
        bne t0, 0, if_zero
        
        li t1, PLAYER_FIRE_DELAY
	move t0, t1
	sw t0, player_fire_time
	
	la t0, player
        lw s0, Object_x(t0)
        lw s1, Object_y(t0)
        lw s2, player_angle
        
        move a0, s0
        move a1, s1
        move a2, s2
        jal bullet_new
if_zero:

leave s0, s1, s2

# ------------------------------------------------------------------------------
.globl player_update_thrust
player_update_thrust:
enter
        lw t0, player_accel
        bne t0, 1, case
        li a0, PLAYER_THRUST
        lw a1, player_angle
        jal to_cartesian
        
        la a0, player
        move a1, v0
        move a2, v1
        jal Object_apply_acceleration
case:
        #la a0, player				#first test
        #li a1, 0				#first test
        #li a2, PLAYER_THRUST			#first test
        #mul a2, a2, -1				#first test
        
leave

# ------------------------------------------------------------------------------
# void player_damage(int dmg)
#   can be called by other objects (like rocks) to damage the player.
#   the argument is how many points of damage to do.
.globl player_damage
player_damage:
enter s0
	move s0, a0
	
	lw t2, player_iframes
	bne t2, 0, done

	lw t0, player_health
	sub t0, t0, s0
	maxi t0, t0, 0
	
	sw t0, player_health
	bne t0, 0, no_health
	
	la t0, player
	lw t1, Object_x(t0)
	lw t2, Object_y(t0)
	
	move a0, t1
	move a1, t2
	jal explosion_new
	
	lw t1, player_lives
	sub t1, t1, 1
	maxi t1, t1, 0
	sw t1, player_lives
	
	li t0, PLAYER_RESPAWN_TIME
	sw t0, player_deadframes
	j done
no_health:
	li t0, PLAYER_HURT_IFRAMES
	sw t0, player_iframes
	j done
done:
leave s0

# ------------------------------------------------------------------------------
# player_collide_all()
# checks if the player collides with anything.
# call the appropriate player-collision function on all active objects that have one.
.globl player_collide_all
player_collide_all:
enter s0, s1, s2
	# s0 = obj
	# s1 = i
	# s2 = collision function

	# start at objects[1]
	la s0, objects
	add s0, s0, Object_sizeof
	li s1, 1
_player_collide_all_loop:
		# don't collide if the player is invulnerable or dead.
		lw   t0, player_deadframes
		bnez t0, _player_collide_all_return
		lw   t0, player_iframes
		bnez t0, _player_collide_all_return

		# s2 = player_collide_funcs[obj.type]
		lw  s2, Object_type(s0)
		sll s2, s2, 2
		la  t0, player_collide_funcs
		add s2, s2, t0
		lw  s2, (s2)

		# skip objects without a collision function
		beq s2, 0, _player_collide_all_continue

		# if Objects_overlap(obj, player)
		move a0, s0
		la   a1, player
		jal  Objects_overlap
		beq  v0, 0, _player_collide_all_continue

			# OKAY, we hit the player
			# call the function (in s2) with the object as the argument
			move a0, s0
			jalr s2

_player_collide_all_continue:
	add s0, s0, Object_sizeof
	inc s1
	blt s1, MAX_OBJECTS, _player_collide_all_loop

_player_collide_all_return:
leave s0, s1, s2
