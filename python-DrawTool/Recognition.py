#2018.09.25 BeibeiZhang
import cv2 as cv

def analysis():
    image=cv.imread("temp.jpg")
    # 二值化图像
    gray = cv.cvtColor(image, cv.COLOR_BGR2GRAY)
    canny=cv.Canny(gray,50,150)
    #cv.imshow("input image", canny)

    out_binary, contours, hierarchy = cv.findContours(canny, cv.RETR_EXTERNAL, cv.CHAIN_APPROX_SIMPLE)

    result=[]
    for cnt in range(len(contours)):
        area = cv.contourArea(contours[cnt])
        # 计算面积
        if(area>=400000 or area<=5000):
            #area>=40000: the framw, area<=50000: a point =>continue
            continue
        # 提取与绘制轮廓
        #cv.drawContours(result, contours, cnt, (0, 255, 0), 2)
        # 轮廓逼近
        #epsilon = 0.01 * cv.arcLength(contours[cnt], True)
        approx = cv.approxPolyDP(contours[cnt],20, True)
        #because we draw shapes by hand, so the epsilon needs to be large, not too exact
       # cv.polylines(result, approx,True, (0, 255, 0), 2)
        hull=cv.convexHull(approx,True)
        corners=len(hull)
        # 利用几何逼近，再凸包分析有几个顶点，由此分析几何形状
        shape_type = ""
        if corners == 3:
            shape_type = "triangle"
        if corners == 4:
            # 计算周长
            p = cv.arcLength(contours[cnt], True)
            side=p/4
            squareArea=side*side
            #use squareArea=side*side to judge if is a square
            if (area<=squareArea+5000 and area>=squareArea-5000):
                shape_type = "square"
            else:
                shape_type = "rectangle"
        if corners >= 8:
            shape_type = "circle"
        if 4 < corners < 8:
            shape_type = "polygon"

        # 求解中心位置
        mm = cv.moments(contours[cnt])
        cx = int(mm['m10'] / mm['m00'])
        cy = int(mm['m01'] / mm['m00'])
        info=[]
        info.append(cx)
        info.append(cy)
        info.append(shape_type)
        result.append(info)
    return result






