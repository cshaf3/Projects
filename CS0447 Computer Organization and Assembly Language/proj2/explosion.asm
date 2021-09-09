.include "constants.asm"
.include "macros.asm"

# =================================================================================================
# Explosions
# =================================================================================================

# void explosion_new(x, y)
.globl explosion_new
explosion_new:
enter s0, s1
	move s0, a0
	move s1, a1
	
	li a0, TYPE_EXPLOSION
	jal Object_new
	
	move t2, v0
	
	beq v0, 0, else
	sw s0, Object_x(v0)
        sw s1, Object_y(v0)
        
        li t0, EXPLOSION_HW
        li t1, EXPLOSION_HH
        sw t0, Object_hw(v0)
        sw t1, Object_hh(v0)
        
        li t0, EXPLOSION_ANIM_DELAY
        li t1, 0
        
        sw t0, Explosion_timer(v0)
        sw t1, Explosion_frame(v0)
else:
leave s0, s1

# ------------------------------------------------------------------------------

.globl explosion_update
explosion_update:
enter s0
	move s0, a0
	
	lw t0, Explosion_timer(s0)
	sub t0, t0, 1
	sw t0, Explosion_timer(s0)
	
	bne t0, 0, del
	
	li t0, EXPLOSION_ANIM_DELAY
	sw t0, Explosion_timer(s0)
	
	lw t1, Explosion_frame(s0)
	add t1, t1, 1
	sw t1, Explosion_frame(s0)
	blt t1, 6, del
	move a0, s0
	jal Object_delete
del:
leave s0

# ------------------------------------------------------------------------------

.globl explosion_draw
explosion_draw:
enter s0
	move s0, a0
	
	lw t0, Explosion_frame(s0)
	la t1, spr_explosion_frames
	
	mul t0, t0, 4
	add t0, t1, t0
	
	lw a1, (t0)
	jal Object_blit_5x5_trans	
leave s0