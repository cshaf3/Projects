 # Caroline Shafer
 # cas369

 .include "lab5_include.asm"

 .data
 frame_counter:    .word 0
 last_frame_time:  .word 0
 dot_x:			   .word 32
 dot_y:			   .word 32
 .text

 .globl main
 main:
     # here is where you would put any initialization stuff that
     # needs to happen *before* the game begins.

 _main_loop:
     lw a0, frame_counter
     div a0, a0, 60
     li v0, 1
     syscall

     li a0, '\n'
     li v0, 11
     syscall
     # check_input()
     jal check_input
     jal draw_dot
     # display_update_and_clear()
     jal display_update_and_clear
     # wait_for_next_frame(16)
     li a0, 16
     jal wait_for_next_frame

     j _main_loop
check_input:
     push ra

     jal input_get_keys

     

     pop ra
     jr ra
draw_dot:
	 #display_set_pixel(dot_x, dot_y, COLOR_WHATEVER);
     push ra

     lw a0, dot_x
     lw a1, dot_y
     li a2, COLOR_ORANGE
     jal display_set_pixel

     pop ra
     jr ra