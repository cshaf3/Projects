# Caroline Shafer
# cas369

.include "macros.asm"
.eqv INPUT_SIZE 3
.eqv DURATION   500
.eqv VOLUME     100
.data
input: .space INPUT_SIZE
# maps from ASCII to MIDI note numbers, or -1 if invalid.
key_to_note_table: .byte
	-1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1
	-1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1
	-1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 60 -1 -1 -1
	75 -1 61 63 -1 66 68 70 -1 73 -1 -1 -1 -1 -1 -1
	-1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1
	-1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1
	-1 -1 55 52 51 64 -1 54 56 72 58 -1 -1 59 57 74
	76 60 65 49 67 71 53 62 50 69 48 -1 -1 -1 -1 -1

demo_notes: .byte
	67 67 64 67 69 67 64 64 62 64 62
	67 67 64 67 69 67 64 62 62 64 62 60
	60 60 64 67 72 69 69 72 69 67
	67 67 64 67 69 67 64 62 64 65 64 62 60
	-1

demo_times: .word
	250 250 250 250 250 250 500 250 750 250 750
	250 250 250 250 250 250 500 375 125 250 250 1000
	375 125 250 250 1000 375 125 250 250 1000
	250 250 250 250 250 250 500 250 125 125 250 250 1000
	0
recorded_notes: .byte  -1:1024
recorded_times: .word 250:1024
.text

# -----------------------------------------------

.globl main
main:
     print_str "Hello! Welcome!\n"
# -----------------------------------------------
_main_loop:
     print_str "Please choose: [k]eyboard, [d]emo, [r]ecord, [p]lay, [q]uit: "
     
     la a0, input
     li a1, INPUT_SIZE
     li v0, 8
     syscall

     lb t0, input
     
     beq t0, 'q', quit
     beq t0, 'k', _case_keyboard
     beq t0, 'd', _case_demo
     beq t0, 'r', _case_record
     beq t0, 'p', _case_play
     
     print_str "Huh?\n"
     j _main_loop
# -----------------------------------------------
quit:
     li v0, 10
     syscall
# -----------------------------------------------
_case_keyboard:
     jal keyboard
     j _main_loop
_case_demo:
     jal demo
     j _main_loop
_case_record:
     jal record
     j _main_loop
_case_play:
     jal play
     j _main_loop
# -----------------------------------------------
keyboard:
     push ra

     println_str "play notes with letters and numbers, ` to change instrument, enter to stop."
     
     j keyboard_loop
     pop ra
     jr ra
keyboard_loop:
     li v0, 12
     syscall
         
     beq v0, '\n', _main_loop
     beq v0, '`', change_instrument
     
     move a0, v0
     jal translate_note
     
     beq v0, -1, keyboard_loop
     move a0, v0
     jal play_note
     
     j keyboard_loop
change_instrument:
     print_str "\nEnter instrument number (1..128): "
     
     li v0, 5
     syscall
     
     blt v0, 0, change_instrument
     bgt v0, 128, change_instrument
     
     sub v0, v0, 1
     move a2, v0
     jal keyboard_loop
play_note:
     push ra
     
     li a1, DURATION 
     li a3, VOLUME  
     li v0, 31
     syscall
     
     pop ra
     jr ra
translate_note:
     push ra
     push s0
     
     move s0, a0
     
     add v0, zero, -1
     blt s0, 0, _main_loop
     bgt s0, 127, _main_loop
     
     la t0, key_to_note_table         # put address of key_to_note_table into t0
     add s0, s0, t0                   # put the index into s0
     lb t1, 0(s0)                     # get the value from the array cell

     move v0, t1
     
     pop s0
     pop ra
     jr ra
# -----------------------------------------------
demo:
     push ra
     
     la a0, demo_notes
     la a1, demo_times
     
     jal play_song

     pop ra
     jr ra
play_song:
     push ra
     push s1
     push s0
     
     move s1, a1
     move s0, a0
play_song_loop:     
     lb t0, 0(s0)
     lw t1, 0(s1)
     
     beq t0, -1, end
     
     move a0, t0
     jal play_note
     
     move a0, t1
     li v0, 32
     syscall
     
     add s0, s0, 1
     add s1, s1, 4
     j play_song_loop
end:
     pop s0
     pop s1
     pop ra
     jr ra
# -----------------------------------------------
record:
     push ra
     push s1
     push s0
     
     move s0, a0    #note array address
     move s1, a1    #time array address
     
     println_str "play notes with letters and numbers"
     
     la s0, recorded_notes
     la s1, recorded_times
     
     add s3, zero, zero
     
     j record_loop
record_end:
     pop s0
     pop s1
     pop ra
     jr ra
     
record_loop:
     li v0, 12
     syscall
      
     beq v0, '\n', end_recorf	#make value -1
     
     move a0, v0		
     jal translate_note
     
     beq v0, -1, record_loop
     
     move a0, v0
     
     sb a0, 0(s0)	#store ascii into pointer
     add s0, s0, 1	#increment pointer
     
     jal play_note
     
     #li v0, 30
     #syscall
     
     #move a0, v0
     
     #sb a0, 0(s1)	#store ascii into pointer
     #add s1, s1, 4	#increment pointer
     
     #add s3, s3, 1	#ARRAY COUNTER
     j record_loop
end_recorf:
     #li v0, 30
     #syscall
     
     #sw v0, 0(s1)
     
     add t2, zero, -1
     sb t2, 0(s0)
     
     #add t0, zero, zero
     #blt t0, s3, math
#math:
     #add t3, s1, 4
     #lw t1, 0(t3)
     #lw t2, 0(s1)
     
     #sub t2, t3, t1
     #sw t3, 0(s1)
     
     #add t0, t0, 1
     j record_end
# -----------------------------------------------
play:
     push ra

     la a0, recorded_notes
     la a1, recorded_times
     jal play_song
     
     pop ra
     jr ra
# -----------------------------------------------
