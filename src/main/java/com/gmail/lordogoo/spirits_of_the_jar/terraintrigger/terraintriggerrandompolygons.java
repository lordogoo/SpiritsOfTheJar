package com.gmail.lordogoo.spirits_of_the_jar.terraintrigger;

import java.awt.*;
import java.lang.Math;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.List;

import com.gmail.lordogoo.spirits_of_the_jar.*;
import com.gmail.lordogoo.spirits_of_the_jar.controlers.*;
import com.gmail.lordogoo.spirits_of_the_jar.gameobject.*;
import org.kynosarges.tektosyne.geometry.PointD;
import org.kynosarges.tektosyne.geometry.RectD;
import org.kynosarges.tektosyne.geometry.VoronoiEdge;
import org.kynosarges.tektosyne.geometry.VoronoiResults;

import static java.lang.Integer.valueOf;


public class terraintriggerrandompolygons extends terraintriggerpolygon
{

    double minX = Double.MAX_VALUE, maxX = 0, minY = Double.MAX_VALUE, maxY = 0;
    double minXV = Double.MAX_VALUE, maxXV = Double.MIN_VALUE, minYV = Double.MAX_VALUE, maxYV = Double.MIN_VALUE;
    ProtoPolygon topLeft,topRight,bottomLeft,bottomRight;

    public terraintriggerrandompolygons()
    {

    }

    public void trigger(menucontrol m,terraininstanciated t,terrainmodel tm,Random r){
        Vector<pointint> pointlist = tm.randomizepoints(200,30,0,0,t.getx(),t.gety(),t.getwidth(),t.getheight());
        
        //Vector<Point> points = new Vector<Point>();
        PointD[] points = new PointD[pointlist.size()];
        double[] xvalues = new double[pointlist.size()];
        double[] yvalues = new double[pointlist.size()];

        for(int i = 0; i < pointlist.size();i++){
            //points.add(new Point(pointlist.get(i).getx(),pointlist.get(i).gety()));
            points[i] = new PointD(pointlist.get(i).getx(),pointlist.get(i).gety());
            xvalues[i] = valueOf(pointlist.get(i).getx());
            yvalues[i] = valueOf(pointlist.get(i).gety());
            if(pointlist.get(i).getx() > maxX){
                maxX = pointlist.get(i).getx();
            }
            if(pointlist.get(i).getx() < minX){
                minX = pointlist.get(i).getx();
            }
            if(pointlist.get(i).gety() > maxY){
                maxY = pointlist.get(i).gety();
            }
            if(pointlist.get(i).gety() < minY){
                minY = pointlist.get(i).gety();
            }
        }




        //Voronoi voronoi = new Voronoi((Collection)points);
        //Voronoi voronoi = new Voronoi(1);
        RectD rect = new RectD(minX,minY,maxX,maxY);
        VoronoiResults result = org.kynosarges.tektosyne.geometry.Voronoi.findAll(points,rect);
        //Graph graph = voronoi.getGraph();
        //List<Voronoi.GraphEdge> graph = voronoi.generateVoronoi(xvalues,yvalues,minX,maxX,minY,maxY);


        for(int i = 0; i < result.voronoiVertices.length; i++) {
            if(result.voronoiVertices[i].x > maxXV){
                maxXV = result.voronoiVertices[i].x;
            }
            if(result.voronoiVertices[i].x < minXV){
                minXV = result.voronoiVertices[i].x;
            }
            if(result.voronoiVertices[i].y > maxYV){
                maxYV = result.voronoiVertices[i].y;
            }
            if(result.voronoiVertices[i].y < minYV){
                minYV = result.voronoiVertices[i].y;
            }
        }



        List<edgevalue> left = new ArrayList<edgevalue>(result.voronoiEdges.length);
        List<edgevalue> right = new ArrayList<edgevalue>(result.voronoiEdges.length);
        List<VoronoiEdge> graphList = new ArrayList<VoronoiEdge>();
        List<List<polygonEdge>> polygons = new ArrayList<>();

        topLeft = new ProtoPolygon(new ProtoVertex(minXV,minYV));
        topRight = new ProtoPolygon(new ProtoVertex(maxXV,minYV));
        bottomLeft = new ProtoPolygon(new ProtoVertex(minXV,maxYV));
        bottomRight = new ProtoPolygon(new ProtoVertex(maxXV,maxYV));

        List<PointD> pointsRight = new ArrayList<>();
        for(int i = 0; i < result.voronoiVertices.length; i++){
            if(result.voronoiVertices[i].x == minXV){
                pointsRight.add(result.voronoiVertices[i]);
            }
        }

        List<ProtoPolygon> ProtoPolygonList = new ArrayList<>();
        ProtoPolygonList.add(topLeft);
        ProtoPolygonList.add(topRight);
        ProtoPolygonList.add(bottomLeft);
        ProtoPolygonList.add(bottomRight);

        try {
            String[] toppoint = {"topLeft","bottomLeft","bottomRight","topRight"};
            String[] bottompoint = {"bottomLeft","bottomRight","topRight","topLeft"};
            String[] test = {"lessThen","lessThen","greaterThen","greaterThen"};
            String[] toplist = {"minYV", "minXV", "maxYV", "maxXV"};
            String[] bottomlist = {"maxYV", "maxXV", "minYV", "minXV"};
            int[] addedDirection = {1,1,-1,-1};
            String[] currentDirection = {"y","x","y","x"};
            String[] testDirection = {"x","y","x","y"};
            String[] testLimit = {"minXV","maxYV","maxXV","minYV"};
            Class[] inputParams = {double.class,double.class};
            for (int d = 0; d < 4; d++) {
                ProtoPolygon toppolygon = (ProtoPolygon)this.getClass().getDeclaredField(toppoint[d]).get(this);
                double top = (double)this.getClass().getDeclaredField(toplist[d]).get(this);
                ProtoPolygon bottompolygon = (ProtoPolygon)this.getClass().getDeclaredField(bottompoint[d]).get(this);
                double bottom = (double)this.getClass().getDeclaredField(bottomlist[d]).get(this);
                while ((Boolean)this.getClass().getDeclaredMethod(test[d],inputParams).invoke(this,top,bottom)/*top < bottom*/) {
                    double distancetop = addedDirection[d]*Double.MAX_VALUE;
                    int nextTopIndex = -1;
                    PointD nextTop = null;
                    for (int i = 0; i < result.voronoiVertices.length; i++) {
                        double deltay = (double)PointD.class.getField(currentDirection[d]).get(result.voronoiVertices[i]) - top;
                        double newdistance = deltay;
                        if (((double)PointD.class.getField(testDirection[d]).get(result.voronoiVertices[i]) == (double)this.getClass().getDeclaredField(testLimit[d]).get(this))
                                && ((Boolean)this.getClass().getDeclaredMethod(test[d],inputParams).invoke(this,0,newdistance))
                                && ((Boolean)this.getClass().getDeclaredMethod(test[d],inputParams).invoke(this,newdistance,distancetop))) {
                            distancetop = newdistance;
                            nextTopIndex = i;
                            nextTop = result.voronoiVertices[i];
                        }
                    }

                    if (nextTop != null) {
                        ProtoPolygon newpolygon = new ProtoPolygon();
                        ProtoPolygonList.add(newpolygon);
                        ProtoVertex newvertex = new ProtoVertex(nextTop.x, nextTop.y, nextTopIndex);
                        newpolygon.addLeft(newvertex);
                        toppolygon.addRight(newvertex);

                        newpolygon.addAdjacentLeft(toppolygon);
                        toppolygon.addAdjacentRight(newpolygon);
                        top = (double)PointD.class.getField(currentDirection[d]).get(nextTop);
                        toppolygon = newpolygon;
                    }else{
                        bottompolygon.addLeft(toppolygon.left.get(0));
                        bottompolygon.addAdjacentLeft(toppolygon.adjacentLeft.get(0));
                        toppolygon.adjacentLeft.get(0).adjacentRight.remove(0);
                        toppolygon.adjacentLeft.get(0).addAdjacentRight(bottompolygon);
                        top = (double)ProtoVertex.class.getField(currentDirection[d]).get(bottompolygon.corner);
                    }
                }
            }
        }catch(NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e){
            e.printStackTrace();
        }

        /*
        //setup
        tm.getworld().numterrainpeice();
        for(int i = 0; i < result.voronoiEdges.length;i++){
            left.add(new edgevalue(result.voronoiEdges[i]));
            right.add(new edgevalue(result.voronoiEdges[i]));
            graphList.add(result.voronoiEdges[i]);
        }

        //run right aligned convex hull to get all polygons
        for(int i = 0; i < graphList.size(); i++) {
            VoronoiEdge e = graphList.get(i);
            boolean rightValue = getValue(e,right);
            if(!rightValue){
                System.out.println("new polygon");
                boolean savedBackword = false;
                ArrayList<polygonEdge> newPolygon = new ArrayList<>();
                newPolygon.add(new polygonEdge(e,savedBackword));
                polygons.add(newPolygon);

                VoronoiEdge currentEdge = e;
                while((currentEdge != null)&&(!getValue(currentEdge,right))) {
                    double theta = Double.MAX_VALUE;
                    VoronoiEdge savedEdge = null;
                    boolean backword = false;
                    int type = -1;
                    for(int j = 0; j < graphList.size(); j++) {
                        VoronoiEdge e2 = graphList.get(j);
                        if(savedBackword){
                            if ((currentEdge.vertex2 != e2.vertex1) && (currentEdge.vertex1 != e2.vertex2) &&
                                    (currentEdge.vertex1 == e2.vertex1) && (currentEdge.vertex2 != e2.vertex2)) {
                                double x1 = result.voronoiVertices[currentEdge.vertex2].x;
                                double x2 = result.voronoiVertices[currentEdge.vertex1].x;
                                double x3 = result.voronoiVertices[e2.vertex2].x;
                                double y1 = result.voronoiVertices[currentEdge.vertex2].y;
                                double y2 = result.voronoiVertices[currentEdge.vertex1].y;
                                double y3 = result.voronoiVertices[e2.vertex2].y;
                                double a1 = x1 - x2;
                                double a2 = x2 - x3;
                                double b1 = y1 - y2;
                                double b2 = y2 - y3;
                                double testTheta = Math.atan((a2 * b1 - a1 * b2) / (a1 * a2 + b1 * b2));
                                if ((testTheta < theta) && (testTheta > 0)) {
                                    theta = testTheta;
                                    savedEdge = e2;
                                    backword = false;
                                    type = 1;
                                }
                            }

                            if ((currentEdge.vertex2 != e2.vertex1) && (currentEdge.vertex1 == e2.vertex2) &&
                                    (currentEdge.vertex1 != e2.vertex1) && (currentEdge.vertex2 != e2.vertex2)) {
                                double x1 = result.voronoiVertices[currentEdge.vertex2].x;
                                double x2 = result.voronoiVertices[currentEdge.vertex1].x;
                                double x3 = result.voronoiVertices[e2.vertex1].x;
                                double y1 = result.voronoiVertices[currentEdge.vertex2].y;
                                double y2 = result.voronoiVertices[currentEdge.vertex1].y;
                                double y3 = result.voronoiVertices[e2.vertex1].y;
                                double a1 = x1 - x2;
                                double a2 = x2 - x3;
                                double b1 = y1 - y2;
                                double b2 = y2 - y3;
                                double testTheta = Math.atan((a2 * b1 - a1 * b2) / (a1 * a2 + b1 * b2));
                                if ((testTheta < theta) && (testTheta > 0)) {
                                    theta = testTheta;
                                    savedEdge = e2;
                                    backword = true;
                                    type = 2;
                                }
                            }
                        }else {
                            if ((currentEdge.vertex1 != e2.vertex1) && (currentEdge.vertex2 != e2.vertex2) &&
                                    (currentEdge.vertex2 == e2.vertex1) && (currentEdge.vertex1 != e2.vertex2)) {
                                double x1 = result.voronoiVertices[currentEdge.vertex1].x;
                                double x2 = result.voronoiVertices[currentEdge.vertex2].x;
                                double x3 = result.voronoiVertices[e2.vertex2].x;
                                double y1 = result.voronoiVertices[currentEdge.vertex1].y;
                                double y2 = result.voronoiVertices[currentEdge.vertex2].y;
                                double y3 = result.voronoiVertices[e2.vertex2].y;
                                double a1 = x1 - x2;
                                double a2 = x2 - x3;
                                double b1 = y1 - y2;
                                double b2 = y2 - y3;
                                double testTheta = Math.atan((a2 * b1 - a1 * b2) / (a1 * a2 + b1 * b2));
                                if ((testTheta < theta) && (testTheta > 0)) {
                                    theta = testTheta;
                                    savedEdge = e2;
                                    backword = false;
                                    type = 3;
                                }
                            }

                            if ((currentEdge.vertex1 != e2.vertex1) && (currentEdge.vertex2 == e2.vertex2) &&
                                    (currentEdge.vertex2 != e2.vertex1) && (currentEdge.vertex1 != e2.vertex2)) {
                                double x1 = result.voronoiVertices[currentEdge.vertex1].x;
                                double x2 = result.voronoiVertices[currentEdge.vertex2].x;
                                double x3 = result.voronoiVertices[e2.vertex1].x;
                                double y1 = result.voronoiVertices[currentEdge.vertex1].y;
                                double y2 = result.voronoiVertices[currentEdge.vertex2].y;
                                double y3 = result.voronoiVertices[e2.vertex1].y;
                                double a1 = x1 - x2;
                                double a2 = x2 - x3;
                                double b1 = y1 - y2;
                                double b2 = y2 - y3;
                                double testTheta = Math.atan((a2 * b1 - a1 * b2) / (a1 * a2 + b1 * b2));
                                if ((testTheta < theta) && (testTheta > 0)) {
                                    theta = testTheta;
                                    savedEdge = e2;
                                    backword = true;
                                    type = 4;
                                }
                            }
                        }
                    }

                    newPolygon.add(new polygonEdge(savedEdge,backword));
                    setValue(currentEdge,true,right);
                    currentEdge = savedEdge;
                    savedBackword = backword;
                }
            }
        }

        //run left aligned convex hull to get all polygons
        for(int i = 0; i < graphList.size(); i++) {
            VoronoiEdge e = graphList.get(i);
            boolean leftValue = getValue(e,left);
            if(!leftValue){
                System.out.println("new polygon");
                boolean savedBackword = false;
                ArrayList<polygonEdge> newPolygon = new ArrayList<>();
                newPolygon.add(new polygonEdge(e,savedBackword));
                polygons.add(newPolygon);

                VoronoiEdge currentEdge = e;
                while((currentEdge != null)&&(!getValue(currentEdge,left))) {
                    double theta = Double.MAX_VALUE;
                    VoronoiEdge savedEdge = null;
                    boolean backword = false;
                    int type = -1;
                    for(int j = 0; j < graphList.size(); j++) {
                        VoronoiEdge e2 = graphList.get(j);
                        if(savedBackword){
                            if ((currentEdge.vertex2 != e2.vertex1) && (currentEdge.vertex1 != e2.vertex2) &&
                                    (currentEdge.vertex1 == e2.vertex1) && (currentEdge.vertex2 != e2.vertex2)) {
                                double x1 = result.voronoiVertices[currentEdge.vertex2].x;
                                double x2 = result.voronoiVertices[currentEdge.vertex1].x;
                                double x3 = result.voronoiVertices[e2.vertex2].x;
                                double y1 = result.voronoiVertices[currentEdge.vertex2].y;
                                double y2 = result.voronoiVertices[currentEdge.vertex1].y;
                                double y3 = result.voronoiVertices[e2.vertex2].y;
                                double a1 = x1 - x2;
                                double a2 = x2 - x3;
                                double b1 = y1 - y2;
                                double b2 = y2 - y3;
                                double testTheta = Math.atan((a2 * b1 - a1 * b2) / (a1 * a2 + b1 * b2));
                                if ((testTheta > theta) && (testTheta < 0)) {
                                    theta = testTheta;
                                    savedEdge = e2;
                                    backword = false;
                                    type = 1;
                                }
                            }

                            if ((currentEdge.vertex2 != e2.vertex1) && (currentEdge.vertex1 == e2.vertex2) &&
                                    (currentEdge.vertex1 != e2.vertex1) && (currentEdge.vertex2 != e2.vertex2)) {
                                double x1 = result.voronoiVertices[currentEdge.vertex2].x;
                                double x2 = result.voronoiVertices[currentEdge.vertex1].x;
                                double x3 = result.voronoiVertices[e2.vertex1].x;
                                double y1 = result.voronoiVertices[currentEdge.vertex2].y;
                                double y2 = result.voronoiVertices[currentEdge.vertex1].y;
                                double y3 = result.voronoiVertices[e2.vertex1].y;
                                double a1 = x1 - x2;
                                double a2 = x2 - x3;
                                double b1 = y1 - y2;
                                double b2 = y2 - y3;
                                double testTheta = Math.atan((a2 * b1 - a1 * b2) / (a1 * a2 + b1 * b2));
                                if ((testTheta > theta) && (testTheta < 0)) {
                                    theta = testTheta;
                                    savedEdge = e2;
                                    backword = true;
                                    type = 2;
                                }
                            }
                        }else {
                            if ((currentEdge.vertex1 != e2.vertex1) && (currentEdge.vertex2 != e2.vertex2) &&
                                    (currentEdge.vertex2 == e2.vertex1) && (currentEdge.vertex1 != e2.vertex2)) {
                                double x1 = result.voronoiVertices[currentEdge.vertex1].x;
                                double x2 = result.voronoiVertices[currentEdge.vertex2].x;
                                double x3 = result.voronoiVertices[e2.vertex2].x;
                                double y1 = result.voronoiVertices[currentEdge.vertex1].y;
                                double y2 = result.voronoiVertices[currentEdge.vertex2].y;
                                double y3 = result.voronoiVertices[e2.vertex2].y;
                                double a1 = x1 - x2;
                                double a2 = x2 - x3;
                                double b1 = y1 - y2;
                                double b2 = y2 - y3;
                                double testTheta = Math.atan((a2 * b1 - a1 * b2) / (a1 * a2 + b1 * b2));
                                if ((testTheta > theta) && (testTheta < 0)) {
                                    theta = testTheta;
                                    savedEdge = e2;
                                    backword = false;
                                    type = 3;
                                }
                            }

                            if ((currentEdge.vertex1 != e2.vertex1) && (currentEdge.vertex2 == e2.vertex2) &&
                                    (currentEdge.vertex2 != e2.vertex1) && (currentEdge.vertex1 != e2.vertex2)) {
                                double x1 = result.voronoiVertices[currentEdge.vertex1].x;
                                double x2 = result.voronoiVertices[currentEdge.vertex2].x;
                                double x3 = result.voronoiVertices[e2.vertex1].x;
                                double y1 = result.voronoiVertices[currentEdge.vertex1].y;
                                double y2 = result.voronoiVertices[currentEdge.vertex2].y;
                                double y3 = result.voronoiVertices[e2.vertex1].y;
                                double a1 = x1 - x2;
                                double a2 = x2 - x3;
                                double b1 = y1 - y2;
                                double b2 = y2 - y3;
                                double testTheta = Math.atan((a2 * b1 - a1 * b2) / (a1 * a2 + b1 * b2));
                                if ((testTheta > theta) && (testTheta < 0)) {
                                    theta = testTheta;
                                    savedEdge = e2;
                                    backword = true;
                                    type = 4;
                                }
                            }
                        }
                    }
                    newPolygon.add(new polygonEdge(savedEdge,backword));
                    setValue(currentEdge,true,left);
                    currentEdge = savedEdge;
                    savedBackword = backword;
                }
            }
        }
        */
        System.out.println("Output List start");
        ProtoPolygon current =  topLeft;
        do{
            if(current.hascorner){
                System.out.println(current.corner.x+","+current.corner.y);
            }
            System.out.println(current.right.get(0).x+","+current.right.get(0).y);
            current = current.adjacentRight.get(0);
        }while((current.right.size() != 0)&&((current.hascorner)&&(current.corner.index != topLeft.corner.index)));
        System.out.println("Output List end");

        //convert polygons to terrain
        int offsetx = t.getworld().getoffsetx();
        int offsety = t.getworld().getoffsety();
        List<Polygon> polygonList = new ArrayList<>();
        for(int i = 0; i < polygons.size(); i++){
            Polygon p = new Polygon();
            for(int j = 0; j < polygons.get(i).size(); j++){
                if(polygons.get(i).get(j).getEdge() != null) {
                    p.addPoint(
                            (int) result.voronoiVertices[polygons.get(i).get(j).getVertex()].x,
                            (int) result.voronoiVertices[polygons.get(i).get(j).getVertex()].y
                    );
                }else{
                    p.addPoint(
                            (int) result.voronoiVertices[polygons.get(i).get(0).getEdge().vertex1].x,
                            (int) result.voronoiVertices[polygons.get(i).get(0).getEdge().vertex1].y
                    );
                }
            }
            p.translate(-offsetx ,-offsety);
            //todo currently getting random. might want to do a height calculation.
            int rand = Math.abs(r.nextInt()%tm.getworld().numterrainpeice());
            terrainpeiceinstanciated peice = new terrainpeiceinstanciated(tm.getworld().getterrainpeice(rand),p);
            t.addterrainpeice(peice);
        }
    }

    public boolean lessThen(double a,double b){
        return a < b;
    }

    public boolean greaterThen(double a,double b){
        return a > b;
    }

    public void setValue(VoronoiEdge edge,boolean value,List<edgevalue> list){
        for(int i = 0; i < list.size();i++){
            if(list.get(i).getEdge().equals(edge)){
                list.get(i).setBoolean(value);
            }
        }
    }

    public boolean getValue(VoronoiEdge edge,List<edgevalue> list){
        for(int i = 0; i < list.size();i++){
            if(list.get(i).getEdge().equals(edge)){
                return list.get(i).getBoolean();
            }
        }
        return false;
    }

    public class edgevalue{
        boolean value;
        VoronoiEdge edge;
        public edgevalue(VoronoiEdge edge){
            value = false;
            this.edge = edge;
        }

        public void setBoolean(boolean bool){
            value = bool;
        }

        public boolean getBoolean(){
            return value;
        }

        public VoronoiEdge getEdge(){
            return edge;
        }
    }

    public class polygonEdge{
        public VoronoiEdge edge;
        //direction of the edge. false means the last should be taken. true means the first.
        public boolean direction;
        public polygonEdge(VoronoiEdge edge,boolean direction){
            this.edge = edge;
            this.direction = direction;
        }

        public VoronoiEdge getEdge(){
            return edge;
        }

        public boolean getDirection(){
            return direction;
        }

        public int getVertex(){
            if(direction){
                return edge.vertex2;
            }else{
                return edge.vertex1;
            }
        }
    }
    
    
    
    
    
    public class ProtoPolygon{
        public boolean hascorner;
        public ProtoVertex corner;
        public List<ProtoVertex> left;
        public List<ProtoPolygon> adjacentLeft;
        public List<ProtoVertex> right;
        public List<ProtoPolygon> adjacentRight;

        public ProtoPolygon(){
            hascorner = false;
            init();
        }

        public ProtoPolygon(ProtoVertex corner){
            hascorner = true;
            this.corner = corner;
            init();
        }

        public void init(){
            left = new ArrayList<>();
            adjacentLeft = new ArrayList<>();
            right = new ArrayList<>();
            adjacentRight = new ArrayList<>();
        }

        public void addLeft(ProtoVertex vertex){
            left.add(vertex);
        }

        public void addAdjacentLeft(ProtoPolygon polygon){
            adjacentLeft.add(polygon);
        }

        public void addRight(ProtoVertex vertex){
            right.add(vertex);
        }

        public void addAdjacentRight(ProtoPolygon polygon){
            adjacentRight.add(polygon);
        }
    }
    
    public class ProtoVertex{
        public int index;
        public double x;
        public double y;

        public ProtoVertex(double x,double y){
            index = -1;
            this.x = x;
            this.y = y;
        }

        public ProtoVertex(double x,double y,int index){
            this.index = index;
            this.x = x;
            this.y = y;
        }
    }
    
}
