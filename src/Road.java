import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Road extends JPanel{
    private TrafficLight light;
    private SpeedSign sign;
    private int numOfSegments; //length of road
    private final int roadWidth = 50;
    final int roadYPos;
    final int endRoadYPos;
    final int roadXPos;
    final int endRoadXPos;
    private Color lightColor = Color.green;
    private Color signColor = Color.green;
    private String orientation;
    String trafficDirection;


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, 1200, 1200);

        for(int i = 0; i < Map.roads.size();i++){
            Road r = Map.roads.get(i);
            r.paintRoad(g);
            if(r.getOrientation().equals("vertical")){
                for (int j = 0; j < Map.cars.size(); j++) {
                    Car car = Map.cars.get(j);
                    if(car.getRoadCarIsOn().equals(r)) {
                        car.paintMeVertical(g);
                    }
                }
                if (r.getTrafficLight() != null) {
                    r.paintLight(g);
                }
                if (r.getSign() != null) {
                    r.paintSign(g);
                }
            }
            else{
                for (int j = 0; j < Map.cars.size(); j++) {
                    Car car = Map.cars.get(j);
                    if(car.getRoadCarIsOn().equals(r)) {
                        car.paintMeHorizontal(g);
                    }
                }
                if (r.getTrafficLight() != null) {
                    r.paintLight(g);
                }
                if (r.getSign() != null) {
                    r.paintSign(g);
                }
            }
        }
    }

    // paints traffic light
    public void paintLight(Graphics g){
        g.setColor(lightColor);
        if(getOrientation().equals("horizontal")) {
            if (getTrafficDirection().equals("east")) {
                g.fillRect(roadXPos + numOfSegments * 25 - 10, roadYPos - 20, 10, 20);
                g.setColor(Color.black);
                g.drawRect(roadXPos + numOfSegments * 25 - 10, roadYPos - 20, 10, 20);
            } else {
                g.fillRect(roadXPos, roadYPos - 20, 10, 20);
                g.setColor(Color.black);
                g.drawRect(roadXPos, roadYPos - 20, 10, 20);
            }
        }
        else{
            if (getTrafficDirection().equals("south")) {
                g.fillRect(roadYPos - 20, roadXPos + numOfSegments * 25 - 10, 20, 10);
                g.setColor(Color.black);
                g.drawRect(roadYPos - 20, roadXPos + numOfSegments * 25 - 10, 20, 10);
            }
            else{
                g.fillRect(roadYPos - 20, roadXPos, 20, 10);
                g.setColor(Color.black);
                g.drawRect(roadYPos - 20, roadXPos, 20, 10);
            }
        }
    }
    //paint Speed up Sign
    public void paintSign(Graphics g){
        g.setColor(signColor);
        if(getOrientation().equals("horizontal")) {
            if (getTrafficDirection().equals("east")) {
                g.fillOval(roadXPos + numOfSegments * 25 - 10 - 50, roadYPos - 20, 20, 20);
                g.setColor(Color.black);
                g.drawOval(roadXPos + numOfSegments * 25 - 10 - 50, roadYPos - 20, 20, 20);
            } else {
                g.fillOval(roadXPos + 50, roadYPos - 20, 20, 20);
                g.setColor(Color.black);
                g.drawOval(roadXPos + 50, roadYPos - 20, 20, 20);
            }
        }
        else{
            if (getTrafficDirection().equals("south")) {
                g.fillOval(roadYPos - 20, roadXPos + numOfSegments * 25 - 10 - 50, 20, 20);
                g.setColor(Color.black);
                g.drawOval(roadYPos - 20, roadXPos + numOfSegments * 25 - 10 - 50, 20, 20);
            }
            else{
                g.fillOval(roadYPos - 20, roadXPos + 40, 20, 10);
                g.setColor(Color.black);
                g.drawOval(roadYPos - 20, roadXPos + 40, 20, 10);
            }
        }
    }

    public void paintRoad(Graphics g){
        if(orientation.equals("horizontal")) {
            g.setColor(Color.black);
            g.fillRect(roadXPos, roadYPos,numOfSegments * 25, roadWidth);
            g.setColor(Color.WHITE);
            for (int j = 0; j < numOfSegments * 25; j = j + 50) { // line being drawn
                g.fillRect(roadXPos + j, roadYPos + roadWidth / 2, 30, 2);
            }
        }
        else{
            g.setColor(Color.black);
            g.fillRect(roadYPos, roadXPos, roadWidth, numOfSegments * 25);
            g.setColor(Color.WHITE);
            for (int j = 0; j < numOfSegments * 25; j = j + 50) { // line being drawn
                g.fillRect( roadYPos + roadWidth / 2, roadXPos + j, 2,30);
            }
        }
    }

    Road(int numOfSegments, String orientation, int xPos, int yPos, String direction){
        super();
        this.numOfSegments = numOfSegments*2;
        this.orientation = orientation;
        this.roadXPos = xPos;
        this.roadYPos = yPos;
        this.endRoadXPos = xPos + numOfSegments * 50;
        this.endRoadYPos = yPos + numOfSegments * 50;
        this.trafficDirection = direction;
    }
    Road(int numOfSegments, String orientation, int xPos, int yPos, String direction, TrafficLight light, SpeedSign sign){
        super();
        this.numOfSegments = numOfSegments*2;
        this.orientation = orientation;
        this.light = light;
        this.sign = sign;
        this.roadXPos = xPos;
        this.roadYPos = yPos;
        this.endRoadXPos = xPos + numOfSegments * 50;
        this.endRoadYPos = yPos + numOfSegments * 50;
        this.trafficDirection = direction;

    }
    Road(int numOfSegments, String orientation, int xPos, int yPos, String direction, TrafficLight light){
        super();
        this.numOfSegments = numOfSegments*2;
        this.orientation = orientation;
        this.light = light;
        this.roadXPos = xPos;
        this.roadYPos = yPos;
        this.endRoadXPos = xPos + numOfSegments * 50;
        this.endRoadYPos = yPos + numOfSegments * 50;
        this.trafficDirection = direction;

    }
    public String getOrientation(){ return orientation;}
    public TrafficLight getTrafficLight(){
        return light;
    }
    public SpeedSign getSign() {return sign;}
    public int getRoadLength(){
        return numOfSegments;
    }
    public int getRoadYPos(){
        return roadYPos;
    }
    public int getRoadXPos(){
        return roadXPos;
    }
    public int getEndRoadYPos(){
        return endRoadYPos;
    }
    public int getEndRoadXPos(){
        return endRoadXPos;
    }
    public String getTrafficDirection(){ return trafficDirection; }
    public void setLightColor(Color c){
        lightColor = c;
    }
    public void setSignColor(Color c){signColor = c;}

}

