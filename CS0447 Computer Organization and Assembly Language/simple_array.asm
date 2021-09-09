# This example simulate the following Java code snippet
#
# int[] array = {5, 2, 6, 1, 3, 9, 8};
# int num = 7;
# int i = 0;
# while(i != num)
# {
#     System.out.print(array[i]);
#     i++;
# }

.data
	array:	.word	5, 2, 6, 1, 3, 9, 8	# int[] array = {5, 2, 6, 1, 3, 9, 8}
	num:	.word	7			# int num = 7; // number of elements of the above array
.text
	# Print every element on the console
	la   $s0, array		# $s0 is the address of array (address of the first element)
	la   $s1, num		# $s1 is the address of num
	lw   $s1, 0($s1)	# $s1 = num
	add  $s2, $zero, $zero	# $s2 = 0 (i = 0)
loop:	beq  $s2, $s1, done	# If $s2 == $s1, goto done
	sll  $s3, $s2, 2	# $s3 = $s2 * 4 (4 is the size of each word)
	add  $s4, $s0, $s3	# $s4 = address of the first element + (i * size)
	lw   $a0, 0($s4)	# $a0 = array[i]
	addi $v0, $zero, 1	# Syscall 1: print integer
	syscall			# Print integer
	addi $s2, $s2, 1	# i++
	j    loop		# Go back to loop
done:	addi $v0, $zero, 10	# Syscall 10: Terminate program
	syscall			# Terminate program

# Note that the above example use shift left logical to perform multiplication. Remember that
# to calculate x * 2^y, we can simply perform x << y (shift-left logical x for y times).

