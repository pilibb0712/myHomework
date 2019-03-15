import MySQLdb
import json
from Drawing import Drawing
import LocationIntChange

def saveDrawing(drawing):
    id=drawing.get_id()
    locations=drawing.get_locations()
    xList,yList=LocationIntChange.locationToInt(locations)

    xStr=json.dumps(xList)
    yStr=json.dumps(yList)
    #change list(x,y) int jsonStr to save in mysql

    db = MySQLdb.connect("localhost", "root", "Bb199861317", "drawTool", charset='utf8')
    cursor=db.cursor()
    sql='insert into drawings values("%s","%s","%s")'
    try:
        cursor.execute(sql % \
                       (id,xStr,yStr))
        db.commit()
    except:
        db.rollback()
    db.close()

def getIdsOfDrawings():
    db = MySQLdb.connect("localhost", "root", "Bb199861317", "drawTool", charset='utf8')
    cursor = db.cursor()
    sql='select id from drawings'
    try:
        cursor.execute(sql)
        result=cursor.fetchall()
        db.commit()
        db.close()
        return result
    except:
        db.rollback()
        db.close()


def getDrawingOfId(targetId):
    db = MySQLdb.connect("localhost", "root", "Bb199861317", "drawTool", charset='utf8')
    cursor = db.cursor()
    sql = "select * from drawings where id='%s'"
    try:
        cursor.execute(sql % \
                       (targetId))
        result=cursor.fetchall()
        db.commit()
        drawingInfo=result[0]
        id=drawingInfo[0]
        xList=json.loads(drawingInfo[1])
        yList=json.loads(drawingInfo[2])
        locationList=LocationIntChange.intToLocation(xList,yList)
        targetDrawing=Drawing()
        targetDrawing.set_id(id)
        targetDrawing.set_locations(locationList)
        db.close()
        return targetDrawing
    except:
        db.rollback()
        db.close()


def deleteDrawing(targetId):
    db = MySQLdb.connect("localhost", "root", "Bb199861317", "drawTool", charset='utf8')
    cursor = db.cursor()
    sql = "delete from drawings where id='%s'"
    try:
        cursor.execute(sql % \
                       (targetId))
        db.commit()
        db.close()
    except:
        db.rollback()
        db.close()








