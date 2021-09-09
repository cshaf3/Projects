#Caroline Shafer
#cas369
.macro print_str %str
	.data
	print_str_message: .asciiz %str
	.text
	la	a0, print_str_message
	li	v0, 4
	syscall
.end_macro
.eqv INPUT_SIZE 3
.data
 display: .word 0
 input: .space INPUT_SIZE
.text
.globl main
main:
print_str "Hello! Welcome!\n"
_main_loop:
     lw a0, display
     li v0, 1
     syscall
     print_str "\nOperation (=,+,-,*,/,c,q): "
     la a0, input
     li a1, INPUT_SIZE
     li v0, 8
     syscall
     lb t0, input
     beq t0, 'q', _quit
     beq t0, 'c', _clear
     beq t0, '+', _get_operand
     beq t0, '-', _get_operand
     beq t0, '*', _get_operand
     beq t0, '/', _get_operand
     beq t0, '=', _get_operand
     print_str "Huh?\n"
     j _main_loop
_quit:
     li v0, 10
     syscall
_clear:
     sw zero, display
     j _main_loop
_get_operand:
     print_str "Value: "
     li v0, 5
     syscall
     move t1, v0
     beq t0, '+', _addition
     beq t0, '-', _subtraction
     beq t0, '*', _multiplication
     beq t0, '/', _division
     beq t0, '=', _equals
_addition:
     lw t0, display
     add t0, t1, t0
     sw display, t0
     j _main_loop
_subtraction:
     lw t0, display
     sub t0, t0, t1
     sw display, t0
     j _main_loop
_multiplication:
     lw t0, display
     mul t0, t0, t1
     sw display, t0
     j _main_loop
_division:
     lw t0, display
     beq t1, 0, _error
     div t0, t0, t1
     sw display, t0
     j _main_loop
_error:
     print_str "Cannot divide by zero!\n"
     j _main_loop
_equals:
     sw display, t1
     j _main_loop
