
./cas369_2:     file format elf32-i386


Disassembly of section .init:

080482f8 <_init>:
 80482f8:	55                   	push   %ebp
 80482f9:	89 e5                	mov    %esp,%ebp
 80482fb:	53                   	push   %ebx
 80482fc:	83 ec 04             	sub    $0x4,%esp
 80482ff:	e8 00 00 00 00       	call   8048304 <_init+0xc>
 8048304:	5b                   	pop    %ebx
 8048305:	81 c3 f4 14 00 00    	add    $0x14f4,%ebx
 804830b:	8b 93 fc ff ff ff    	mov    -0x4(%ebx),%edx
 8048311:	85 d2                	test   %edx,%edx
 8048313:	74 05                	je     804831a <_init+0x22>
 8048315:	e8 1e 00 00 00       	call   8048338 <__gmon_start__@plt>
 804831a:	e8 01 01 00 00       	call   8048420 <frame_dummy>
 804831f:	e8 bc 02 00 00       	call   80485e0 <__do_global_ctors_aux>
 8048324:	58                   	pop    %eax
 8048325:	5b                   	pop    %ebx
 8048326:	c9                   	leave  
 8048327:	c3                   	ret    

Disassembly of section .plt:

08048328 <__gmon_start__@plt-0x10>:
 8048328:	ff 35 fc 97 04 08    	pushl  0x80497fc
 804832e:	ff 25 00 98 04 08    	jmp    *0x8049800
 8048334:	00 00                	add    %al,(%eax)
	...

08048338 <__gmon_start__@plt>:
 8048338:	ff 25 04 98 04 08    	jmp    *0x8049804
 804833e:	68 00 00 00 00       	push   $0x0
 8048343:	e9 e0 ff ff ff       	jmp    8048328 <_init+0x30>

08048348 <fgets@plt>:
 8048348:	ff 25 08 98 04 08    	jmp    *0x8049808
 804834e:	68 08 00 00 00       	push   $0x8
 8048353:	e9 d0 ff ff ff       	jmp    8048328 <_init+0x30>

08048358 <__libc_start_main@plt>:
 8048358:	ff 25 0c 98 04 08    	jmp    *0x804980c
 804835e:	68 10 00 00 00       	push   $0x10
 8048363:	e9 c0 ff ff ff       	jmp    8048328 <_init+0x30>

08048368 <printf@plt>:
 8048368:	ff 25 10 98 04 08    	jmp    *0x8049810
 804836e:	68 18 00 00 00       	push   $0x18
 8048373:	e9 b0 ff ff ff       	jmp    8048328 <_init+0x30>

08048378 <puts@plt>:
 8048378:	ff 25 14 98 04 08    	jmp    *0x8049814
 804837e:	68 20 00 00 00       	push   $0x20
 8048383:	e9 a0 ff ff ff       	jmp    8048328 <_init+0x30>

Disassembly of section .text:

08048390 <_start>:
 8048390:	31 ed                	xor    %ebp,%ebp
 8048392:	5e                   	pop    %esi
 8048393:	89 e1                	mov    %esp,%ecx
 8048395:	83 e4 f0             	and    $0xfffffff0,%esp
 8048398:	50                   	push   %eax
 8048399:	54                   	push   %esp
 804839a:	52                   	push   %edx
 804839b:	68 70 85 04 08       	push   $0x8048570
 80483a0:	68 80 85 04 08       	push   $0x8048580
 80483a5:	51                   	push   %ecx
 80483a6:	56                   	push   %esi
 80483a7:	68 5a 85 04 08       	push   $0x804855a
 80483ac:	e8 a7 ff ff ff       	call   8048358 <__libc_start_main@plt>
 80483b1:	f4                   	hlt    
 80483b2:	90                   	nop
 80483b3:	90                   	nop
 80483b4:	90                   	nop
 80483b5:	90                   	nop
 80483b6:	90                   	nop
 80483b7:	90                   	nop
 80483b8:	90                   	nop
 80483b9:	90                   	nop
 80483ba:	90                   	nop
 80483bb:	90                   	nop
 80483bc:	90                   	nop
 80483bd:	90                   	nop
 80483be:	90                   	nop
 80483bf:	90                   	nop

080483c0 <__do_global_dtors_aux>:
 80483c0:	55                   	push   %ebp
 80483c1:	89 e5                	mov    %esp,%ebp
 80483c3:	53                   	push   %ebx
 80483c4:	83 ec 04             	sub    $0x4,%esp
 80483c7:	80 3d 20 98 04 08 00 	cmpb   $0x0,0x8049820
 80483ce:	75 3f                	jne    804840f <__do_global_dtors_aux+0x4f>
 80483d0:	a1 24 98 04 08       	mov    0x8049824,%eax
 80483d5:	bb 24 97 04 08       	mov    $0x8049724,%ebx
 80483da:	81 eb 20 97 04 08    	sub    $0x8049720,%ebx
 80483e0:	c1 fb 02             	sar    $0x2,%ebx
 80483e3:	83 eb 01             	sub    $0x1,%ebx
 80483e6:	39 d8                	cmp    %ebx,%eax
 80483e8:	73 1e                	jae    8048408 <__do_global_dtors_aux+0x48>
 80483ea:	8d b6 00 00 00 00    	lea    0x0(%esi),%esi
 80483f0:	83 c0 01             	add    $0x1,%eax
 80483f3:	a3 24 98 04 08       	mov    %eax,0x8049824
 80483f8:	ff 14 85 20 97 04 08 	call   *0x8049720(,%eax,4)
 80483ff:	a1 24 98 04 08       	mov    0x8049824,%eax
 8048404:	39 d8                	cmp    %ebx,%eax
 8048406:	72 e8                	jb     80483f0 <__do_global_dtors_aux+0x30>
 8048408:	c6 05 20 98 04 08 01 	movb   $0x1,0x8049820
 804840f:	83 c4 04             	add    $0x4,%esp
 8048412:	5b                   	pop    %ebx
 8048413:	5d                   	pop    %ebp
 8048414:	c3                   	ret    
 8048415:	8d 74 26 00          	lea    0x0(%esi,%eiz,1),%esi
 8048419:	8d bc 27 00 00 00 00 	lea    0x0(%edi,%eiz,1),%edi

08048420 <frame_dummy>:
 8048420:	55                   	push   %ebp
 8048421:	89 e5                	mov    %esp,%ebp
 8048423:	83 ec 18             	sub    $0x18,%esp
 8048426:	a1 28 97 04 08       	mov    0x8049728,%eax
 804842b:	85 c0                	test   %eax,%eax
 804842d:	74 12                	je     8048441 <frame_dummy+0x21>
 804842f:	b8 00 00 00 00       	mov    $0x0,%eax
 8048434:	85 c0                	test   %eax,%eax
 8048436:	74 09                	je     8048441 <frame_dummy+0x21>
 8048438:	c7 04 24 28 97 04 08 	movl   $0x8049728,(%esp)
 804843f:	ff d0                	call   *%eax
 8048441:	c9                   	leave  
 8048442:	c3                   	ret    
 8048443:	90                   	nop

08048444 <s>:
 8048444:	55                   	push   %ebp
 8048445:	89 e5                	mov    %esp,%ebp
 8048447:	8b 55 08             	mov    0x8(%ebp),%edx
 804844a:	b8 00 00 00 00       	mov    $0x0,%eax
 804844f:	80 3a 00             	cmpb   $0x0,(%edx)
 8048452:	74 09                	je     804845d <s+0x19>
 8048454:	83 c0 01             	add    $0x1,%eax
 8048457:	80 3c 02 00          	cmpb   $0x0,(%edx,%eax,1)
 804845b:	75 f7                	jne    8048454 <s+0x10>
 804845d:	5d                   	pop    %ebp
 804845e:	c3                   	ret    

0804845f <c>:
 804845f:	55                   	push   %ebp
 8048460:	89 e5                	mov    %esp,%ebp
 8048462:	53                   	push   %ebx
 8048463:	83 ec 04             	sub    $0x4,%esp
 8048466:	8b 5d 08             	mov    0x8(%ebp),%ebx
 8048469:	85 db                	test   %ebx,%ebx
 804846b:	74 28                	je     8048495 <c+0x36>
 804846d:	89 1c 24             	mov    %ebx,(%esp)
 8048470:	e8 cf ff ff ff       	call   8048444 <s>
 8048475:	85 c0                	test   %eax,%eax
 8048477:	7e 1c                	jle    8048495 <c+0x36>
 8048479:	89 1c 24             	mov    %ebx,(%esp)
 804847c:	e8 c3 ff ff ff       	call   8048444 <s>
 8048481:	80 7c 03 ff 0a       	cmpb   $0xa,-0x1(%ebx,%eax,1)
 8048486:	75 0d                	jne    8048495 <c+0x36>
 8048488:	89 1c 24             	mov    %ebx,(%esp)
 804848b:	e8 b4 ff ff ff       	call   8048444 <s>
 8048490:	c6 44 03 ff 00       	movb   $0x0,-0x1(%ebx,%eax,1)
 8048495:	83 c4 04             	add    $0x4,%esp
 8048498:	5b                   	pop    %ebx
 8048499:	5d                   	pop    %ebp
 804849a:	c3                   	ret    

0804849b <r>:
 804849b:	55                   	push   %ebp
 804849c:	89 e5                	mov    %esp,%ebp
 804849e:	56                   	push   %esi
 804849f:	53                   	push   %ebx
 80484a0:	83 ec 04             	sub    $0x4,%esp
 80484a3:	8b 75 08             	mov    0x8(%ebp),%esi
 80484a6:	8b 5d 0c             	mov    0xc(%ebp),%ebx
 80484a9:	89 34 24             	mov    %esi,(%esp)
 80484ac:	e8 93 ff ff ff       	call   8048444 <s>
 80484b1:	0f b6 13             	movzbl (%ebx),%edx
 80484b4:	84 d2                	test   %dl,%dl
 80484b6:	74 24                	je     80484dc <r+0x41>
 80484b8:	8d 44 06 ff          	lea    -0x1(%esi,%eax,1),%eax
 80484bc:	3a 10                	cmp    (%eax),%dl
 80484be:	74 09                	je     80484c9 <r+0x2e>
 80484c0:	eb 13                	jmp    80484d5 <r+0x3a>
 80484c2:	83 e8 01             	sub    $0x1,%eax
 80484c5:	3a 10                	cmp    (%eax),%dl
 80484c7:	75 0c                	jne    80484d5 <r+0x3a>
 80484c9:	83 c3 01             	add    $0x1,%ebx
 80484cc:	0f b6 13             	movzbl (%ebx),%edx
 80484cf:	84 d2                	test   %dl,%dl
 80484d1:	75 ef                	jne    80484c2 <r+0x27>
 80484d3:	eb 07                	jmp    80484dc <r+0x41>
 80484d5:	b8 00 00 00 00       	mov    $0x0,%eax
 80484da:	eb 05                	jmp    80484e1 <r+0x46>
 80484dc:	b8 01 00 00 00       	mov    $0x1,%eax
 80484e1:	83 c4 04             	add    $0x4,%esp
 80484e4:	5b                   	pop    %ebx
 80484e5:	5e                   	pop    %esi
 80484e6:	5d                   	pop    %ebp
 80484e7:	c3                   	ret    

080484e8 <d>:
 80484e8:	55                   	push   %ebp
 80484e9:	89 e5                	mov    %esp,%ebp
 80484eb:	53                   	push   %ebx
 80484ec:	81 ec 84 00 00 00    	sub    $0x84,%esp
 80484f2:	a1 1c 98 04 08       	mov    0x804981c,%eax
 80484f7:	89 44 24 08          	mov    %eax,0x8(%esp)
 80484fb:	c7 44 24 04 64 00 00 	movl   $0x64,0x4(%esp)
 8048502:	00 
 8048503:	8d 5d 94             	lea    -0x6c(%ebp),%ebx
 8048506:	89 1c 24             	mov    %ebx,(%esp)
 8048509:	e8 3a fe ff ff       	call   8048348 <fgets@plt>
 804850e:	89 1c 24             	mov    %ebx,(%esp)
 8048511:	e8 49 ff ff ff       	call   804845f <c>
 8048516:	89 5c 24 04          	mov    %ebx,0x4(%esp)
 804851a:	89 1c 24             	mov    %ebx,(%esp)
 804851d:	e8 79 ff ff ff       	call   804849b <r>
 8048522:	85 c0                	test   %eax,%eax
 8048524:	74 1f                	je     8048545 <d+0x5d>
 8048526:	89 1c 24             	mov    %ebx,(%esp)
 8048529:	e8 16 ff ff ff       	call   8048444 <s>
 804852e:	83 f8 0c             	cmp    $0xc,%eax
 8048531:	7e 12                	jle    8048545 <d+0x5d>
 8048533:	89 5c 24 04          	mov    %ebx,0x4(%esp)
 8048537:	c7 04 24 34 86 04 08 	movl   $0x8048634,(%esp)
 804853e:	e8 25 fe ff ff       	call   8048368 <printf@plt>
 8048543:	eb 0c                	jmp    8048551 <d+0x69>
 8048545:	c7 04 24 64 86 04 08 	movl   $0x8048664,(%esp)
 804854c:	e8 27 fe ff ff       	call   8048378 <puts@plt>
 8048551:	81 c4 84 00 00 00    	add    $0x84,%esp
 8048557:	5b                   	pop    %ebx
 8048558:	5d                   	pop    %ebp
 8048559:	c3                   	ret    

0804855a <main>:
 804855a:	55                   	push   %ebp
 804855b:	89 e5                	mov    %esp,%ebp
 804855d:	83 e4 f0             	and    $0xfffffff0,%esp
 8048560:	e8 83 ff ff ff       	call   80484e8 <d>
 8048565:	89 ec                	mov    %ebp,%esp
 8048567:	5d                   	pop    %ebp
 8048568:	c3                   	ret    
 8048569:	90                   	nop
 804856a:	90                   	nop
 804856b:	90                   	nop
 804856c:	90                   	nop
 804856d:	90                   	nop
 804856e:	90                   	nop
 804856f:	90                   	nop

08048570 <__libc_csu_fini>:
 8048570:	55                   	push   %ebp
 8048571:	89 e5                	mov    %esp,%ebp
 8048573:	5d                   	pop    %ebp
 8048574:	c3                   	ret    
 8048575:	66 66 2e 0f 1f 84 00 	data32 nopw %cs:0x0(%eax,%eax,1)
 804857c:	00 00 00 00 

08048580 <__libc_csu_init>:
 8048580:	55                   	push   %ebp
 8048581:	89 e5                	mov    %esp,%ebp
 8048583:	57                   	push   %edi
 8048584:	56                   	push   %esi
 8048585:	53                   	push   %ebx
 8048586:	e8 4f 00 00 00       	call   80485da <__i686.get_pc_thunk.bx>
 804858b:	81 c3 6d 12 00 00    	add    $0x126d,%ebx
 8048591:	83 ec 1c             	sub    $0x1c,%esp
 8048594:	e8 5f fd ff ff       	call   80482f8 <_init>
 8048599:	8d bb 20 ff ff ff    	lea    -0xe0(%ebx),%edi
 804859f:	8d 83 20 ff ff ff    	lea    -0xe0(%ebx),%eax
 80485a5:	29 c7                	sub    %eax,%edi
 80485a7:	c1 ff 02             	sar    $0x2,%edi
 80485aa:	85 ff                	test   %edi,%edi
 80485ac:	74 24                	je     80485d2 <__libc_csu_init+0x52>
 80485ae:	31 f6                	xor    %esi,%esi
 80485b0:	8b 45 10             	mov    0x10(%ebp),%eax
 80485b3:	89 44 24 08          	mov    %eax,0x8(%esp)
 80485b7:	8b 45 0c             	mov    0xc(%ebp),%eax
 80485ba:	89 44 24 04          	mov    %eax,0x4(%esp)
 80485be:	8b 45 08             	mov    0x8(%ebp),%eax
 80485c1:	89 04 24             	mov    %eax,(%esp)
 80485c4:	ff 94 b3 20 ff ff ff 	call   *-0xe0(%ebx,%esi,4)
 80485cb:	83 c6 01             	add    $0x1,%esi
 80485ce:	39 fe                	cmp    %edi,%esi
 80485d0:	72 de                	jb     80485b0 <__libc_csu_init+0x30>
 80485d2:	83 c4 1c             	add    $0x1c,%esp
 80485d5:	5b                   	pop    %ebx
 80485d6:	5e                   	pop    %esi
 80485d7:	5f                   	pop    %edi
 80485d8:	5d                   	pop    %ebp
 80485d9:	c3                   	ret    

080485da <__i686.get_pc_thunk.bx>:
 80485da:	8b 1c 24             	mov    (%esp),%ebx
 80485dd:	c3                   	ret    
 80485de:	90                   	nop
 80485df:	90                   	nop

080485e0 <__do_global_ctors_aux>:
 80485e0:	55                   	push   %ebp
 80485e1:	89 e5                	mov    %esp,%ebp
 80485e3:	53                   	push   %ebx
 80485e4:	83 ec 04             	sub    $0x4,%esp
 80485e7:	a1 18 97 04 08       	mov    0x8049718,%eax
 80485ec:	83 f8 ff             	cmp    $0xffffffff,%eax
 80485ef:	74 13                	je     8048604 <__do_global_ctors_aux+0x24>
 80485f1:	bb 18 97 04 08       	mov    $0x8049718,%ebx
 80485f6:	66 90                	xchg   %ax,%ax
 80485f8:	83 eb 04             	sub    $0x4,%ebx
 80485fb:	ff d0                	call   *%eax
 80485fd:	8b 03                	mov    (%ebx),%eax
 80485ff:	83 f8 ff             	cmp    $0xffffffff,%eax
 8048602:	75 f4                	jne    80485f8 <__do_global_ctors_aux+0x18>
 8048604:	83 c4 04             	add    $0x4,%esp
 8048607:	5b                   	pop    %ebx
 8048608:	5d                   	pop    %ebp
 8048609:	c3                   	ret    
 804860a:	90                   	nop
 804860b:	90                   	nop

Disassembly of section .fini:

0804860c <_fini>:
 804860c:	55                   	push   %ebp
 804860d:	89 e5                	mov    %esp,%ebp
 804860f:	53                   	push   %ebx
 8048610:	83 ec 04             	sub    $0x4,%esp
 8048613:	e8 00 00 00 00       	call   8048618 <_fini+0xc>
 8048618:	5b                   	pop    %ebx
 8048619:	81 c3 e0 11 00 00    	add    $0x11e0,%ebx
 804861f:	e8 9c fd ff ff       	call   80483c0 <__do_global_dtors_aux>
 8048624:	59                   	pop    %ecx
 8048625:	5b                   	pop    %ebx
 8048626:	c9                   	leave  
 8048627:	c3                   	ret    
