# FINAL PROJECT
# Will McCullough and Jonah Kadens
import turtle
import random
import tkinter as tk
from tkinter import *

#dictionary with colors for randomization
colors  = ["red","green","blue","orange","purple","pink","yellow"]

#creates the first triangle that all the random dots are based on
def drawTriangle(t,p1,p2,p3):
    t.up()
    t.goto(p1)
    t.down()
    t.goto(p2)
    t.goto(p3)
    t.goto(p1)
    
#creates the first square that all the random dots are based on    
def drawSquare(t,p1,p2,p3,p4):
    t.up()
    t.goto(p1)
    t.down()
    t.goto(p2)
    t.goto(p3)
    t.goto(p4)
    t.goto(p1)
    
#function to find the midpoint between two points
def midPoint(p1,p2):
    return ((p1[0]+p2[0])/2.0,(p1[1]+p2[1])/2.0)

#function that creates a random dot at a triangle's vertex
# Retrieved from https://stackoverflow.com/questions/47410054/generate-random-locations-within-a-triangular-domain
# Retrieved on 4/23/18
def RandomDot(p1,p2,p3):
    s,t=sorted([random.random(),random.random()])
    return (s*p1[0]+(t-s)*p2[0]+(1-t)*p3[0],s*p1[1]+(t-s)*p2[1]+(1-t)*p3[1])
    
#function that creates a random dot at a square's vertex    
def RandomSqrDot(p1,p2,p3,p4):
    x=random.randint(int(p1[0]),int(p2[0]))
    y=random.randint(int(p1[1]),int(p3[1]))
    return (x,y)     

fred = turtle.Turtle()
win = turtle.Screen()


root = tk.Tk()
frame = tk.Frame(root)
root.title('Fractals') #titles the button box

#creates top Label
top_frame = tk.Frame(root)
top_frame.pack()

#input prompt
prompt_label = tk.Label(top_frame, \
       text='Enter a number of iterations (more is better):')
prompt_label.pack()

#creates space for user input
mid_frame = tk.Frame(root)
mid_frame.pack()
itr_string = tk.StringVar()
iterations = tk.Entry(mid_frame,textvariable=itr_string)
iterations.pack()


#buttons at the bottom
bottom_frame = tk.Frame(root)
bottom_frame.pack()

#creates a quit button
button = tk.Button(bottom_frame, 
                   text="QUIT", 
                   fg="red",
                   command=quit)
button.pack(side=tk.LEFT)

#main sierpinski function
def sierpinski(t,p1,p2,p3):
    itr = int(iterations.get())
    drawTriangle(t,p1,p2,p3) #creates the first triangle
    t.up()
    t.shape('circle') 
    color = random.choice(colors) #picks a random color for a dot
    t.color(color) #sets turtle to random color
    t.speed(0)
    currentpoint=RandomDot(p1,p2,p3)
    while (itr>0): 
        r=random.randint(1,3) #picks a vertex
        if(r==1): #places a dot at one of the verticies in the triangle
            currentpoint=midPoint(currentpoint,p1)
            t.goto(currentpoint)
            t.dot(3)
            t.up()
            color = random.choice(colors)
            t.color(color)
        elif(r==2): #places a dot at a different vertex than the one above
            currentpoint=midPoint(currentpoint,p2)
            t.goto(currentpoint)
            t.dot(3)
            t.up()
            t.color(color)
        elif(r==3): #places a dot at a different vertex than the two above and prevents an infinite loop
            currentpoint=midPoint(currentpoint,p3)
            t.goto(currentpoint)
            t.dot(3)
            t.up()  
            t.color(color)
            itr=itr-1 
        else: 
            t.goto(0,0)
            
#creates a button to run the sierpinski function
#lambda method allows program to call function after the button is pressed
sTriangle = tk.Button(bottom_frame,
                   text="Triangle",
                   command=lambda: sierpinski(fred, (-250,-250), (250,-250), (0,250)))
sTriangle.pack(side=tk.LEFT)

#main fractal square program                      
def fractalsquare(t,p1,p2,p3,p4):
    itr = int(iterations.get())
    prevCount=[10,10]
    drawSquare(t,p1,p2,p3,p4)
    t.up()
    t.shape('circle')
    color = random.choice(colors) #picks a random color for a dot
    t.color(color)
    t.speed(0)
    currentPoint=RandomSqrDot(p1,p2,p3,p4)
    t.goto(currentPoint)
    t.dot(3)
    while(itr>0):
        r=random.randint(1,4)
        if(r==1):
            if(prevCount[-1]!=1):
                currentPoint=midPoint(currentPoint,p1)
                t.goto(currentPoint)
                t.dot(3)
                prevCount.append(1)
                color = random.choice(colors)
                t.color(color)
                itr=itr-1
                
        elif(r==2):
            if(prevCount[-1]!=2):
                currentPoint=midPoint(currentPoint,p2)
                t.goto(currentPoint)
                t.dot(3)
                prevCount.append(2)
                itr=itr-1
                
        elif(r==3):
            if (prevCount[-1]!=3):
                currentPoint=midPoint(currentPoint,p3)
                t.goto(currentPoint)
                t.dot(3)
                prevCount.append(3)
                itr=itr-1
             
        elif(r==4):
            if (prevCount[-1]!=4):
                currentPoint=midPoint(currentPoint,p4)
                t.goto(currentPoint)
                t.dot(3)
                prevCount.append(4)
                itr=itr-1
#creates a button to run the fractalsquare funcion
fsquare = tk.Button(bottom_frame,
                    text="Square",
                    command=lambda: fractalsquare(fred,(-250,-250),(250,-250),(250,250),(-250,250)))
fsquare.pack(side=tk.LEFT)

root.mainloop()

win.exitonclick()