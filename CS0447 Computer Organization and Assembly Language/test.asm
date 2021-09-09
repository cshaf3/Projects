.data
arr: .int 1, -2, 6, -4, 11

.text
_start:
	lea  (arr), %rbx
	mov  $4, %rdi
	lea  (%rbx, %rdi, 4), %rax
	movl (%rax), %eax
