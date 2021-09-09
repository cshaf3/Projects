# Caroline Shafer
# cas369

.eqv DISPLAY_CTRL  0xFFFF0000
.eqv DISPLAY_KEYS  0xFFFF0004
.eqv DISPLAY_BASE  0xFFFF0008
.eqv COLOR_BLACK   0
.eqv COLOR_RED     1
.eqv COLOR_ORANGE  2
.eqv COLOR_YELLOW  3
.eqv COLOR_GREEN   4
.eqv COLOR_BLUE    5
.eqv COLOR_MAGENTA 6
.eqv COLOR_WHITE   7

.globl main
main:
	sw zero, DISPLAY_CTRL
	li t0, 0x06050401
	sw t0, DISPLAY_BASE
	sw zero, DISPLAY_CTRL
	jal draw_horiz_line
	sw zero, DISPLAY_CTRL
	# exit
	li v0, 10
	syscall
 # -----------------------------------------
 draw_horiz_line:
	add t0, t0, 1
	li, t1, DISPLAY_BASE
	add, t1, t0, 10
	li t2, COLOR_BLUE
	sb t2, (t1)
	blt t0, 10, draw_horiz_line
     	jr ra
 # -----------------------------------------
