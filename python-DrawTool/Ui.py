#2018.09.19 Beibei Zhang:begin ui

from tkinter import *
import tkinter.messagebox as messagebox
from Drawing import Drawing
import Data
import time
from Location import Location
import Recognition
from PIL import ImageGrab

def clickOpen():
    global  allFiles,top,lb
    allFiles = StringVar()
    allFiles.set(list(Data.getIdsOfDrawings()))
    top=Toplevel()
    lb=Listbox(top,listvariable=allFiles)
    #lb.selection_set(0,2)
    lb.bind('<Double-Button-1>',clickFile)
    lb.pack(side=LEFT)
#    print(lb.get(lb.curselection()))



def clickNew():
    canvas.delete(ALL)


def clickFile(event):
    global currentFile,lb
    index = lb.curselection()[0]
    file=lb.get(index)
    currentFile = lb.get(index)
    canvas.delete(ALL)
    canvas.delete(ALL)
    drawing=Data.getDrawingOfId(file)
    locations=drawing.get_locations()
    if locations:
        for location in locations:
            canvas.create_oval(location.get_x() - _thickness, location.get_y() - _thickness, location.get_x() + _thickness, location.get_y() + _thickness, fill='black',
                               outline='black')
        clickRecognize()


def clickSave():
    drawing = Drawing()
    theId=time.strftime("%Y-%m-%d_%H:%M:%S", time.localtime())
    #the format of time:2018-09-20_12:23:00
    drawing.set_id(theId)
    drawing.set_locations(_locations)
    Data.saveDrawing(drawing)
    global allFiles
    allFiles.set(list(Data.getIdsOfDrawings()))
    messagebox._show("", "Save successfully!")
    # update the openMenu



def clickRecognize():
    # pic = ImageGrab.grab((canvasX, canvasY, canvasWidth, canvasHeight))
    #pic = ImageGrab.grab(bbox=canvas.bbox(ALL))
    pic=ImageGrab.grab((_rootx+10,_rooty+50,_rootx+_rootWidth,_rooty+_rootHeight+40))
    #ImageGrab.grab().show()
    #width=800,height=600
    pic.save("temp.jpg")
    result=Recognition.analysis()
    global canvas
    for i in range(0,len(result)):
        info=result[i]
        canvas.create_text(info[0],info[1],text=info[2],fill="blue")


def clickDraw():
    global _ifClean
    _ifClean = False
    global canvas
    canvas.config(cursor='pencil')
    #change the shape of mouse

def clickClean():
    global _ifClean
    _ifClean=True
    global canvas
    canvas.config(cursor='circle')
    # change the shape of mouse

def clickDelete():
    if (currentFile):
        Data.deleteDrawing(currentFile)
        global canvas
        canvas.delete(ALL)
        global allFiles
        allFiles.set(list(Data.getIdsOfDrawings()))
        messagebox._show("", "Delete successfully!")
        # update the openFileMenu



def _drawInCavas(event):
    x=event.x
    y=event.y
    location=Location()
    location.setXY(x,y)
    if(_ifClean):
        canvas.create_oval(x-_thickness,y-_thickness,x+_thickness,y+_thickness,fill='white',outline='white')
        #clean the place where the mouse pass by
        if(_locations):
            #_recordX is not null, the return true
            if location in _locations:
                _locations.remove(location)
    else:
        canvas.create_oval(x - _thickness, y - _thickness, x + _thickness, y + _thickness, fill='black',outline='black')
        # draw the place where the mouse pass by
        if location not in _locations:
            _locations.append(location)



global _ifClean
_ifClean=False
global _locations
_locations=[]
#location: present the current drawing
#how to save the canvas: record the draw locations and the clean locations (draw append, clean remove), finally save the final draw locations
global currentFile
currentFile=''
global fileNum
fileNum=0
global allFiles



global _thickness,_rootHeight,_rootWidth,_rootx,_rooty
_thickness=3
_rootWidth=800
_rootHeight=600
_rootx=200
_rooty=100

root=Tk()
root.title('DrawTool')
root.geometry(str(_rootWidth)+'x'+str(_rootHeight)+'+'+str(_rootx)+'+'+str(_rooty))

global menubar
menubar=Menu(root)
menubar.add_command(label='new',command=clickNew)
menubar.add_cascade(label="open",command=clickOpen)
menubar.add_command(label='save',command=clickSave)
menubar.add_command(label='recognize',command=clickRecognize)
menubar.add_command(label='draw',command=clickDraw)
menubar.add_command(label='clean',command=clickClean)
menubar.add_command(label='delete',command=clickDelete)
menubar.add_command(label='close',command=root.quit)

global canvas
canvas=Canvas(root,width=_rootWidth,height=_rootHeight,background='white')
canvas.bind("<B1-Motion>",_drawInCavas)
canvas.pack()

root.config(menu=menubar)
root.mainloop()







