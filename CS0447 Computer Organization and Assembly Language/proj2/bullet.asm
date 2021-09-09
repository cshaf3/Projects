.include "constants.asm"
.include "macros.asm"

# =================================================================================================
# Bullet
# =================================================================================================

# void bullet_new(x: a0, y: a1, angle: a2)
.globl bullet_new
bullet_new:
enter s0, s1, s2
	move s0, a0
	move s1, a1
	move s2, a2
	
	li a0, TYPE_BULLET
	jal Object_new
	
	move t2, v0
	
	beq v0, 0, else
	sw s0, Object_x(v0)
        sw s1, Object_y(v0)
        
        li a0, BULLET_THRUST
        move a1, s2
        jal to_cartesian
        
        move t0, v0
        move t1, v1
        
        sw t0, Object_vx(t2)
        sw t1, Object_vy(t2)
        
        move s0, t2
        
        li t0, BULLET_LIFE
 	sw t0, Bullet_frame(s0)
else:	
leave s0, s1, s2

# ------------------------------------------------------------------------------

.globl bullet_update
bullet_update:
enter s0
	move s0, a0
	
	lw t0, Bullet_frame(s0)
	sub t0, t0, 1
	sw t0, Bullet_frame(s0)
	
	beq t0, 0, yes_zero
	
	jal Object_accumulate_velocity
	jal Object_wrap_position
	j exit
yes_zero:
	jal Object_delete
exit:
leave s0

# ------------------------------------------------------------------------------

.globl bullet_draw
bullet_draw:
enter s0
	move s0, a0
	lw a0, Object_x(s0)
	sra a0, a0, 8
	lw a1, Object_y(s0)
	sra a1, a1, 8
	li a2, COLOR_RED
	
	jal display_set_pixel
leave s0
