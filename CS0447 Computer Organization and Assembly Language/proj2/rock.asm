.include "constants.asm"
.include "macros.asm"

# =================================================================================================
# Rocks
# =================================================================================================

.globl rocks_count
rocks_count:
enter
	la t0, objects
	li t1, 0
	li v0, 0

	_rocks_count_loop:
		lw t2, Object_type(t0)
		beq t2, TYPE_ROCK_L, _rocks_count_yes
		beq t2, TYPE_ROCK_M, _rocks_count_yes
		bne t2, TYPE_ROCK_S, _rocks_count_continue
		_rocks_count_yes:
			inc v0
	_rocks_count_continue:
	add t0, t0, Object_sizeof
	inc t1
	blt t1, MAX_OBJECTS, _rocks_count_loop
leave

# ------------------------------------------------------------------------------

# void rocks_init(int num_rocks)
.globl rocks_init
rocks_init:
enter s0
	#for i < int numrocks
	move s0, a0
	li s1, 0
loop:
	beq s1, s0, end
	
	li a0, 0x2000
	jal random
	
	add t0, v0, 0x3000
	
	li t3, 0x4000
	div t0, t3
	mfhi t1				#MOD, t1 now x coordinate
	
	li a0, 0x2000
	jal random
	
	add t0, v0, 0x3000
	
	div t0, t3
	mfhi t2				#MOD, t2 now y coordinate
	
	move a0, t1
	move a1, t2
	li a2, TYPE_ROCK_L
	jal rock_new
	
	add s1, s1, 1
	j loop
end:
leave s0

# ------------------------------------------------------------------------------

# void rock_new(x, y, type)
rock_new:
enter s0, s1, s2, s3
	move s0, a0
	move s1, a1
	move s2, a2
	
	move a0, s2
	jal Object_new
	
	move s3, v0		#ROCK OBJECT
	
	beq t0, 0, else
	sw s0, Object_x(s3)
        sw s1, Object_y(s3)
        
        move t0, a2
        li t1, TYPE_ROCK_L
        li t2, TYPE_ROCK_M
        li t3, TYPE_ROCK_S
        beq t0, t1, large
        beq t0, t2, medium
        beq t0, t3, small
large:
	li t1, ROCK_L_HW
        li t2, ROCK_L_HH
        
        sw t1, Object_hw(s3)
        sw t2, Object_hh(s3)
        
        li a0, 360		#range: 360
    	jal random
    	
    	move a1, v0
    	li a0, ROCK_VEL
    	jal to_cartesian
    	
        sw v0, Object_vx(s3)
        sw v1, Object_vy(s3)
medium:
	li t1, ROCK_M_HW
        li t2, ROCK_M_HH
        
        sw t1, Object_hw(s3)
        sw t2, Object_hh(s3)
        
        li a0, 360		#range: 360
    	jal random
    	
    	move a1, v0
    	li t0, ROCK_VEL
    	mul t0, t0, 4
    	move a0, t0
    	jal to_cartesian
    	
        sw v0, Object_vx(s3)
        sw v1, Object_vy(s3)
small:
        li t1, ROCK_S_HW
        li t2, ROCK_S_HH
        
        sw t1, Object_hw(s3)
        sw t2, Object_hh(s3)

        li a0, 360		#range: 360
    	jal random
    	
    	move a1, v0
    	li t0, ROCK_VEL
    	mul t0, t0, 4
    	move a0, t0
    	jal to_cartesian
    	
        sw v0, Object_vx(s3)
        sw v1, Object_vy(s3)
else:	
leave s0, s1, s2, s3

# ------------------------------------------------------------------------------

.globl rock_update
rock_update:
enter s0
	move s0, a0
	
	jal Object_accumulate_velocity
	jal Object_wrap_position
	jal rock_collide_with_bullets
leave s0

# ------------------------------------------------------------------------------

rock_collide_with_bullets:
enter s0, s1, s2
	move s0, a0				#rock object
	la s1, objects				#objects array
	li s2, 0				#index
array_loop:
	lw t1, Object_type(s1)			#get object_type of object
	li t2, TYPE_BULLET			
	
	bne t2, t1, not_in			#if object_type isn't TYPE_BULLET leave
	
	move a0, s0				#obj
	lw a1, Object_x(s1)			#x
	lw a2, Object_y(s1)			#y
	
	jal Object_contains_point		#Object_contains_point(obj, x, y)
	bne v0, 1, not_in			#if false, leave

	move a0, s0				#rock
	jal rock_get_hit			
	
	move a0, s1				#bullet
	jal Object_delete
	j hit
not_in:
	add s1, s1, Object_sizeof		#increment pointer
	inc s2
	ble s2, MAX_OBJECTS, array_loop
hit:
leave s0, s1, s2

# ------------------------------------------------------------------------------

rock_get_hit:
enter s0
	move s0, a0
	lw t0, Object_type(s0)
	
        li t1, TYPE_ROCK_L
        li t2, TYPE_ROCK_M
        li t3, TYPE_ROCK_S
        
        beq t0, t1, large_rock
        beq t0, t2, medium_rock
        
        j delete
large_rock:
	lw a0, Object_x(s0)
	lw a1, Object_y(s0)
	li a2, TYPE_ROCK_M
	jal rock_new
	
	lw a0, Object_x(s0)
	lw a1, Object_y(s0)
	li a2, TYPE_ROCK_M
	jal rock_new
	j delete
medium_rock:
	lw a0, Object_x(s0)
	lw a1, Object_y(s0)
	li a2, TYPE_ROCK_S
	jal rock_new
	
	lw a0, Object_x(s0)
	lw a1, Object_y(s0)
	li a2, TYPE_ROCK_S
	jal rock_new
	j delete
delete:
	move a0, s0
	
	lw a0, Object_x(s0)
	lw a1, Object_y(s0)
	
	jal explosion_new
	
	move a0, s0
	jal Object_delete
leave s0

# ------------------------------------------------------------------------------

.globl rock_collide_l
rock_collide_l:
enter
	jal rock_get_hit
	li a0, 3
	jal player_damage
leave

# ------------------------------------------------------------------------------

.globl rock_collide_m
rock_collide_m:
enter
	jal rock_get_hit
	li a0, 2
	jal player_damage
leave

# ------------------------------------------------------------------------------

.globl rock_collide_s
rock_collide_s:
enter
	jal rock_get_hit
	li a0, 1
	jal player_damage
leave

# ------------------------------------------------------------------------------

.globl rock_draw_l
rock_draw_l:
enter s0
	move s0, a0
	la a1, spr_rock_l
	jal Object_blit_5x5_trans
leave s0

# ------------------------------------------------------------------------------

.globl rock_draw_m
rock_draw_m:
enter
	move s0, a0
	la a1, spr_rock_m
	jal Object_blit_5x5_trans
leave

# ------------------------------------------------------------------------------

.globl rock_draw_s
rock_draw_s:
enter
	move s0, a0
	la a1, spr_rock_s
	jal Object_blit_5x5_trans
leave
