from bmp import *

def getWidth(path):
    image_file = open(path, "rb")

    image_file.seek(18)
    width = unpack('i', image_file.read(4))[0]
    height = unpack('i', image_file.read(4))[0]
    return width
def getHeight(path):
    image_file = open(path, "rb")

    image_file.seek(18)
    width = unpack('i', image_file.read(4))[0]
    height = unpack('i', image_file.read(4))[0]
    return height
def Invert():
    pixels = ReadBMP('img.bmp')
    width = getWidth('img.bmp')
    height = getHeight('img.bmp')
    for x in range(height):
        for y in range(width):
            pixels[x][y][0] = 255 - pixels[x][y][0]
            pixels[x][y][1] = 255 - pixels[x][y][1]
            pixels[x][y][2] = 255 - pixels[x][y][2]
    WriteBMP(pixels, 'inverted.bmp')
#Invert()
def DisplayChannel():
    pixels = ReadBMP('img.bmp')
    width = getWidth('img.bmp')
    height = getHeight('img.bmp')
    n = int(input("Which channel do you want to display? \n(0) for Red, (1) for Green and (2) for Blue\n"))
    if n == 0:
        for x in range(height):
            for y in range(width):
                pixels[x][y][1] = 0
                pixels[x][y][2] = 0
    elif n == 1:
        for x in range(height):
            for y in range(width):
                pixels[x][y][0] = 0
                pixels[x][y][2] = 0
    elif n == 2:
        for x in range(height):
            for y in range(width):
                pixels[x][y][0] = 0
                pixels[x][y][1] = 0
    WriteBMP(pixels, 'channel.bmp')
#DisplayChannel()
def Flip():
    pixels = ReadBMP('img.bmp')
    width = getWidth('img.bmp')
    height = getHeight('img.bmp')
    mirrored = ReadBMP('img.bmp')
    resp = input("Do you want the image flipped horizontally or vertically?\n")
    if resp == 'horizontally':
        for x in range(height):
            for y in range(width):
                mirrored[x][y][0] = pixels[x][(width-1)-y][0]
                mirrored[x][y][1] = pixels[x][(width-1)-y][1]
                mirrored[x][y][2] = pixels[x][(width-1)-y][2]
    elif resp == 'vertically':
        for x in range(height):
            for y in range(width):
                mirrored[x][y][0] = pixels[(height-1)-x][y][0]
                mirrored[x][y][1] = pixels[(height-1)-x][y][1]
                mirrored[x][y][2] = pixels[(height-1)-x][y][2]
    WriteBMP(mirrored, 'flipped.bmp')
#Flip()
def DrawRect():
    pixels = ReadBMP('img.bmp')
    print("Enter coordinates for A (x1, y1) and B (x2, y2)")
    xi = int(input("x1: "))
    yi = int(input("y1: "))
    xii = int(input("x2: "))
    yii =int(input("y2: "))
    for y in range(xi,xii):
        x = yii
        pixels[x][y][0] = 255
        pixels[x][y][1] = 0
        pixels[x][y][2] = 0
    for y in range(xi,xii):
        x = yi
        pixels[x][y][0] = 255
        pixels[x][y][1] = 0
        pixels[x][y][2] = 0
    for x in range (yii,yi):
        y = xi
        pixels[x][y][0] = 255
        pixels[x][y][1] = 0
        pixels[x][y][2] = 0
    for x in range (yii,yi):
        y = xii
        pixels[x][y][0] = 255
        pixels[x][y][1] = 0
        pixels[x][y][2] = 0

    WriteBMP(pixels, 'rectangle.bmp')
#DrawRect()
def Grayscale():
    pixels = ReadBMP('img.bmp')
    width = getWidth('img.bmp')
    height = getHeight('img.bmp')
    for x in range(height):
        for y in range(width):
            total = pixels[x][y][0]+pixels[x][y][1]+pixels[x][y][2]
            pixels[x][y][0] = total//3
            pixels[x][y][1] = total//3
            pixels[x][y][2] = total//3
    WriteBMP(pixels, 'grayscale.bmp')
#Grayscale()
def Brightness():
    pixels = ReadBMP('img.bmp')
    width = getWidth('img.bmp')
    height = getHeight('img.bmp')
    while True:
        n = int(input("Do you want to increase(1) or decrease(2) brightness? Press 0 to quit.\n"))
        if n == 1:
            for x in range(height):
                for y in range(width):
                    over = pixels[x][y][0]
                    if over+25>255:
                        pixels[x][y][0] = 255
                    else:
                        pixels[x][y][0] += 25
                    over = pixels[x][y][1]
                    if over+25>255:
                        pixels[x][y][1] = 255
                    else:
                        pixels[x][y][1] += 25
                    over = pixels[x][y][2]
                    if over+25>255:
                        pixels[x][y][2] = 255
                    else:
                        pixels[x][y][2] += 25
        elif n == 2:
            for x in range(height):
                for y in range(width):
                    under = pixels[x][y][0]
                    if under-25<0:
                        pixels[x][y][0] = 0
                    else:
                        pixels[x][y][0] -= 25
                    under = pixels[x][y][1]
                    if under-25<0:
                        pixels[x][y][1] = 0
                    else:
                        pixels[x][y][1] -= 25
                    under = pixels[x][y][2]
                    if under-25<0:
                        pixels[x][y][2] = 0
                    else:
                        pixels[x][y][2] -= 25
        elif n == 0:
            WriteBMP(pixels, 'brightness.bmp')
            break
#Brightness()
def Contrast():
    pixels = ReadBMP('img.bmp')
    width = getWidth('img.bmp')
    height = getHeight('img.bmp')
    while True:
        n = int(input("Do you want to increase(1) or decrease(2) contrast? Press 0 to quit.\n"))
        if n == 1:
            for x in range(height):
                for y in range(width):
                    newvalue = int(1.4238*(pixels[x][y][0]-128)+128)
                    if newvalue<0:
                        pixels[x][y][0] = 0
                    elif newvalue>255:
                        pixels[x][y][0] = 255
                    else:
                        pixels[x][y][0] = newvalue
                    newvalue = int(1.4238*(pixels[x][y][1]-128)+128)
                    if newvalue<0:
                        pixels[x][y][1] = 0
                    elif newvalue>255:
                        pixels[x][y][1] = 255
                    else:
                        pixels[x][y][1] = newvalue
                    newvalue = int(1.4238*(pixels[x][y][2]-128)+128)
                    if newvalue<0:
                        pixels[x][y][2] = 0
                    elif newvalue>255:
                        pixels[x][y][2] = 255
                    else:
                        pixels[x][y][2] = newvalue
        elif n == 2:
            for x in range(height):
                for y in range(width):
                    newvalue = int(0.7016*(pixels[x][y][0]-128)+128)
                    if newvalue<0:
                        pixels[x][y][0] = 0
                    elif newvalue>255:
                        pixels[x][y][0] = 255
                    else:
                        pixels[x][y][0] = newvalue
                    newvalue = int(0.7016*(pixels[x][y][1]-128)+128)
                    if newvalue<0:
                        pixels[x][y][1] = 0
                    elif newvalue>255:
                        pixels[x][y][1] = 255
                    else:
                        pixels[x][y][1] = newvalue
                    newvalue = int(0.7016*(pixels[x][y][2]-128)+128)
                    if newvalue<0:
                        pixels[x][y][2] = 0
                    elif newvalue>255:
                        pixels[x][y][2] = 255
                    else:
                        pixels[x][y][2] = newvalue
        elif n == 0:
            WriteBMP(pixels, 'contrast.bmp')
            break
#Contrast()
