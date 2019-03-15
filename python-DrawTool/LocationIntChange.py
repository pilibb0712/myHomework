from Location import Location

def locationToInt(locations):
    xs=[]
    ys=[]
    if locations:
        for location in locations:
            x=location.get_x()
            y=location.get_y()
            xs.append(x)
            ys.append(y)
    return xs,ys

def intToLocation(xs,ys):
    locations=[]
    if xs:
        for i in range(0,len(xs)):
            location=Location()
            location.setXY(xs[i],ys[i])
            locations.append(location)
    return locations


