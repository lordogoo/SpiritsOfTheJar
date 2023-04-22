package com.gmail.lordogoo.spirits_of_the_jar.gameinterface;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.event.*;
import info.clearthought.layout.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.awt.font.*;
import java.applet.*;
import java.io.*;
import java.util.*;
import com.gmail.lordogoo.spirits_of_the_jar.*;
import com.gmail.lordogoo.spirits_of_the_jar.controlers.*;
import com.gmail.lordogoo.spirits_of_the_jar.cardgame.*;
import com.gmail.lordogoo.spirits_of_the_jar.gametrigger.*;
import com.gmail.lordogoo.spirits_of_the_jar.gametrigger.battletriggers.*;
import com.gmail.lordogoo.spirits_of_the_jar.gametrigger.combattriggers.*;
import com.gmail.lordogoo.spirits_of_the_jar.gametrigger.dialogtriggers.*;
import com.gmail.lordogoo.spirits_of_the_jar.gametrigger.generictriggers.*;
import com.gmail.lordogoo.spirits_of_the_jar.gametrigger.npctriggers.*;
import com.gmail.lordogoo.spirits_of_the_jar.gametrigger.screentransitiontriggers.*;
import com.gmail.lordogoo.spirits_of_the_jar.gametrigger.shoptriggers.*;
import com.gmail.lordogoo.spirits_of_the_jar.playergame.*;
import com.gmail.lordogoo.spirits_of_the_jar.gameanimation.*;
import com.gmail.lordogoo.spirits_of_the_jar.badguygame.*;
import com.gmail.lordogoo.spirits_of_the_jar.bodygame.*;
import com.gmail.lordogoo.spirits_of_the_jar.gamefaction.*;
import com.gmail.lordogoo.spirits_of_the_jar.events.*;
import com.gmail.lordogoo.spirits_of_the_jar.gameobject.*;
import com.gmail.lordogoo.spirits_of_the_jar.victorygame.*;
import com.gmail.lordogoo.spirits_of_the_jar.destinationrulegame.*;
import com.gmail.lordogoo.spirits_of_the_jar.terraintrigger.*;
import com.gmail.lordogoo.spirits_of_the_jar.gamecardai.*;
import com.gmail.lordogoo.spirits_of_the_jar.activationrulegame.*;
import com.gmail.lordogoo.spirits_of_the_jar.interfacecomponents.*;
import com.gmail.lordogoo.spirits_of_the_jar.gamepopup.*;
import com.gmail.lordogoo.spirits_of_the_jar.ruleconditionalgame.*;
import com.gmail.lordogoo.spirits_of_the_jar.gamenpcai.*;
import com.gmail.lordogoo.spirits_of_the_jar.gameinterface.*;
/**
 * Write a description of class matchmenupanel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class matchmenupanel extends gamepanel
{

    texturelibrary lib;
    gamecontrol game;
    matchmodel match;
    
    //game board dimentions
    int gbx;
    int gby;
    
    //displaycomponents 
    DefaultListModel<handcarddisplay> ophandmodel;
    backgroundlist ophand;
    JScrollPane ophandscroll;
    playerinfodisplay oppointcounter;
    
    DefaultListModel<handcarddisplay> handmodel;
    backgroundlist hand;
    handclick hc;
    JScrollPane handscroll;
    playerinfodisplay pointcounter;
    
    JPanel gameboardpanel;
    int gameboardx;
    int gameboardy;
    gameboardtiledisplay[][] gameboarddisplaylist;
    
    

    JButton passturn;
    JButton discardcard;
    JButton retreat;
    
    Vector<handcarddisplay> handlist;
    
    //game information
    int selectedindex;
    instansiatedcard selectedcard;    
    handcarddisplay selecteddisplay;
    
    //flags
    boolean active;
    boolean choice;
    
    
    
    
    public matchmenupanel(String n,menucontrol c,texturelibrary i,soundlibrary s,int bi)
    {
        super(n,c,i,s,bi);
        lib = i;
        
        double size[][] = 
        {{TableLayout.FILL,20,.2,.2,.2,.2,20,TableLayout.FILL},
        {TableLayout.FILL,20,100,.7,100,20,20,TableLayout.FILL,1}};
        
        this.setdimensions(6,7);
        
        this.setLayout(new TableLayout(size));
        this.setVisible(true);
        
        panel = new specialboarderpanel(i.gettexture(4).getimage(),10,null);
        this.add(panel,"1,1,6,6");
        
        ophandmodel = new DefaultListModel();
        ophand = new backgroundlist(ophandmodel,lib.gettexture(2).getimage());
        ophand.setFixedCellWidth(64);
        ophand.setFixedCellWidth(64);
        ophand.setCellRenderer(new CustomCellRenderer());
        ophand.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        ophand.setVisibleRowCount(-1);
        ophandscroll = new JScrollPane(ophand);
        this.add(ophandscroll,"2,2,4,2");
        this.setComponentZOrder(ophandscroll,0);
        componentlist.add(ophandscroll);
        
        oppointcounter = new playerinfodisplay(i.gettexture(2).getimage(),true);
        oppointcounter.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(oppointcounter,"5,2");
        this.setComponentZOrder(oppointcounter,0);
        componentlist.add(oppointcounter);
        
        //
        gameboardpanel = new backgroundpanel(i.gettexture(2).getimage());
        double sizegbp[][] = 
        {{TableLayout.FILL},
        {TableLayout.FILL}};
        gameboardpanel.setLayout(new TableLayout(sizegbp));
        gameboardpanel.setVisible(true);
        this.add(gameboardpanel,"2,3,5,3");
        this.setComponentZOrder(gameboardpanel,0);
        componentlist.add(gameboardpanel);
        
        pointcounter = new playerinfodisplay(i.gettexture(2).getimage(),false);
        pointcounter.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(pointcounter,"2,4");
        this.setComponentZOrder(pointcounter,0);
        componentlist.add(pointcounter);
        
        handmodel = new DefaultListModel();
        hand = new backgroundlist(handmodel,lib.gettexture(2).getimage());
        hand.setFixedCellWidth(64);
        hc = new handclick();
        hand.addListSelectionListener(hc);
        hand.addMouseListener(new handrightclick());
        hand.setCellRenderer(new CustomCellRenderer());
        hand.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        hand.setVisibleRowCount(-1);
        handscroll = new JScrollPane(hand);
        this.add(handscroll,"3,4,5,4");
        this.setComponentZOrder(handscroll,0);
        componentlist.add(handscroll);
        
        passturn = new JButton("pass turn");
        passturn.addActionListener(new passclick());
        this.add(passturn,"2,5,3,5");
        this.setComponentZOrder(passturn,0);
        componentlist.add(passturn);
        
        discardcard = new JButton("discard card");
        this.add(discardcard,"4,5,5,5");
        discardcard.addActionListener(new discardclick());
        this.setComponentZOrder(discardcard,0);
        componentlist.add(discardcard);
        
        JButton retreat = new JButton("retreat");
        
        
        
    }
    
    public void switchto(menucontrol g){
        game = g.getcurrentprofile().getgame();
        match = game.getcurrentmatch();
        gbx = match.getgameboardx();
        gby = match.getgameboardy();
        
        //create gameboard
        double xpart[] = new double[gbx+2];
        double ypart[] = new double[gby+2];
        xpart[0] = TableLayout.FILL;
        xpart[gbx+1] = TableLayout.FILL;
        ypart[0] = TableLayout.FILL;
        ypart[gby+1] = TableLayout.FILL;
        
        for(int i = 1; i <= gbx;i++){
            xpart[i] = 1.0d/((double)(gbx+2));
        }
        for(int j = 1; j <= gby;j++){
            ypart[j] = 1.0d/((double)(gbx+2));
        }
        
        double size[][]  = {xpart,ypart};
        gameboarddisplaylist = new gameboardtiledisplay[gbx][gby];
        
        JPanel gameboard = new backgroundpanel(lib.gettexture(2).getimage());
        gameboard.setLayout(new TableLayout(size));
        for(int i = 0; i < gbx;i++){
            for(int j = 0; j < gby;j++){
                gameboardtiledisplay temp = new gameboardtiledisplay(i,j,match.getgameboard().gettile(i,j));
                temp.setContentAreaFilled(false);
                temp.addMouseMotionListener(new boardmouseover());
                temp.addMouseListener(new boardmouselistener());
                gameboard.add(temp,(i+1)+","+(j+1));
                gameboarddisplaylist[i][j] = temp; 
            }
        }
       
        gameboardpanel.removeAll();        
        gameboardpanel.add(gameboard,"0,0");
        gameboardpanel.revalidate();
        discardcard.setEnabled(false);
        
        //initilize hands
        handmodel.clear();
        ophandmodel.clear();
        hand.setFixedCellHeight(hand.getHeight());
        ophand.setFixedCellHeight(hand.getHeight());
        
        this.repaint();
        
        //set up animations and triggers
        this.addtrigger(new triggerscreentransitionend());
        trigger cointrigger = new triggercoinanimation(cont,match,lib.getanimation(0));
        this.addtrigger(cointrigger);

        //run triggers
        this.runtriggers(this);
        
    }
    
    public void switchaway(gamecontrol game){
        if(active == true){
            //animationthread.stopthread();
            active = false;
        }
    }
    
    public void disableall(){
        hand.setEnabled(false);
        passturn.setEnabled(false);
        discardcard.setEnabled(false);
        for(int i = 0; i < gbx;i++){
            for(int j = 0; j < gby;j++){
                gameboarddisplaylist[i][j].setEnabled(false);
            }
        }
    }
    
    public void enableall(){
        
        hand.setEnabled(true);
        passturn.setEnabled(true);
        for(int i = 0; i < gbx;i++){
            for(int j = 0; j < gby;j++){
                gameboarddisplaylist[i][j].setEnabled(true);
            }
        }
    }
    
    
    /************************
     * game sequenc functions
     ***********************/
    public void reloadplayerhand(){
        handmodel.clear();
        for(int i = 0; i < match.getplayerhand().size();i++){
            handmodel.addElement(new handcarddisplay(match.getplayerhand().elementAt(i),false,cont.getlib().gettexture(5).getimage()));
        }
    }
    
    public void reloadopponenthand(){
        ophandmodel.clear();
        for(int i = 0; i < match.getopponenthand().size();i++){
            //TODO
            //please change to hide cards when done debugging
            ophandmodel.addElement(new handcarddisplay(match.getopponenthand().elementAt(i),false,cont.getlib().gettexture(5).getimage()));
        }
    }
    
    
    public matchmodel getmatch(){
        return match;
    }
     
    public void addplayerhand(handcarddisplay card){
        handmodel.addElement(card);
    }
    
    public void createplayerhand(Vector<handcarddisplay> cards){
        for(int i = 0; i < cards.size();i++){
            handmodel.addElement(cards.elementAt(i));
        }
    }
    
    //get player component functions
    public JComponent getplayerhand(){
        return handscroll;
    }
    
    public JComponent getplayerdeck(){
        return pointcounter;
    }
    
    public JComponent getplayerdiscard(){
        return null;
    }
    
    public JComponent getgameboardpanel(){
        return gameboardpanel;
    }
    
    public gameboardtiledisplay[][] getgameboarddisplaylist(){
        return gameboarddisplaylist;
    }
    
    //TODO
    public void createopponenthand(Vector<handcarddisplay> cards){  
        for(int i = 0; i < cards.size();i++){
            ophandmodel.addElement(cards.elementAt(i));
        }
    }
    
    public void addopponenthand(handcarddisplay card){
        ophandmodel.addElement(card);
    }
    
    //get opponent component functions
    public synchronized JComponent getopponenthand(){
        return ophandscroll;
    }
    
    public synchronized JComponent getopponentdeck(){
        return oppointcounter;
    }
    
    public JComponent getopponentdiscard(){
        return null;
    }
    
    //get gameboard functions
    public int getgameboardx(){
        return gbx;
    }
    
    public int getgameboardy(){
        return gby;
    }
    
    public JButton getgametile(int x,int y){
        return gameboarddisplaylist[x][y];
    }
    
    public JPanel getgameboard(){
        return gameboardpanel;
    }
    
    /***************************************
     * action listeners
     ***************************************/

    
    private class passclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            if(selectedcard != null){
                 handmodel.addElement(selecteddisplay);
                 match.getplayerhand().add(selectedcard);
                 selecteddisplay = null;
                 selectedcard = null;
                 selectedindex = -1;
                 setCursor(Cursor.getDefaultCursor());
                 discardcard.setEnabled(false);
            }
            thisref.addtrigger(new triggerplayerendturn(cont,match));
            thisref.runtriggers(thisref);
        }  
    }
    
    private class discardclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            if(selectedcard != null){
                selecteddisplay = null;
                selectedcard = null;
                selectedindex = -1;
                setCursor(Cursor.getDefaultCursor());
                thisref.addtrigger(new triggerplayerendturn(cont,match));
                thisref.runtriggers(thisref);
                discardcard.setEnabled(false);
            }
        }
    }
    
    private class handclick implements ListSelectionListener
    {    
        boolean rightclick;
        public handclick(){rightclick = false;}
        
        public void setrightclick(boolean rc){rightclick = rc;}
        
        public void valueChanged(ListSelectionEvent e){
            if(!e.getValueIsAdjusting()){
                if(rightclick == false){
                    if(hand.getSelectedValue()!=null){
                        if(selectedcard == null){
                            //get info
                            selecteddisplay = (handcarddisplay)hand.getSelectedValue();
                            selectedcard = selecteddisplay.getcard();
                            selectedindex = hand.getSelectedIndex();
                            //remove card from hand
                            match.getplayerhand().remove(selectedindex);
                            handmodel.remove(selectedindex);
                            //change cursor
                            Cursor c;
                            Toolkit tk = Toolkit.getDefaultToolkit();
                            c = tk.createCustomCursor(selectedcard.getcard().getsymbol().getimage(), new Point(0,0),"cursorName");
                            setCursor(c);
                            //check game board
                            for(int i = 0; i < gbx; i++){
                                for(int j = 0;j < gby; j++){
                                    gameboarddisplaylist[i][j].changeactive(selectedcard); 
                                }
                            }
                            discardcard.setEnabled(true);
                        }else{           
                            match.getplayerhand().add(selectedcard);
                            handmodel.addElement(selecteddisplay);
                            selectedcard = null;
                            selectedindex = -1;
                            hand.clearSelection(); 
                            //reloadplayerhand();
                            setCursor(Cursor.getDefaultCursor());
                            for(int i = 0; i < gbx; i++){
                                for(int j = 0;j < gby; j++){
                                    gameboarddisplaylist[i][j].changeinactive(); 
                                }
                            }
                            discardcard.setEnabled(false);
                        }
                    }
                }else{
                    handcarddisplay displaytemp = (handcarddisplay)hand.getSelectedValue();
                    if(displaytemp != null){
                    
                        int index = hand.getSelectedIndex();
                        int displaytempw = hand.getFixedCellWidth();
                        int displaytemph = hand.getHeight();
                        int displaytempx = (index * displaytempw) + handscroll.getX();
                        int displaytempy = 0 + handscroll.getY();

                        triggercarddisplayanimation trig = new triggercarddisplayanimation(
                                                                                   displaytempx,displaytempy,displaytempw,displaytemph,
                                                                                   panel.getX(),panel.getY(),panel.getWidth(),panel.getHeight(),
                                                                                   displaytemp.gettexture(),displaytemp.getcard());
                        trig.trigger(thisref);
                        //deselect card
                        selectedindex = -1;
                        hand.clearSelection();
                    
                    }
                    hc.setrightclick(false);
                }
            }
        }
    }
    

     private class handrightclick implements MouseListener{
        public void mousePressed(MouseEvent e) {
            if (SwingUtilities.isRightMouseButton(e)){
                hc.setrightclick(true);
                //hand.setSelectedIndex(hand.locationToIndex((e.getPoint())));
                try{
                        Robot robot = new java.awt.Robot();
                        robot.mousePress(InputEvent.BUTTON1_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_MASK);
                }catch (AWTException ae) { 
                    System.out.println(ae); 
                }
            }
        
        }
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mouseClicked(MouseEvent e) {}
    }
    
    private class boardmouselistener implements  MouseListener{
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON3) { 
                //if(((gameboardtiledisplay)e.getComponent()).){
                    int displaytempw = e.getComponent().getWidth();
                    int displaytemph = e.getComponent().getHeight();
                    int displaytempx = e.getComponent().getX()+gameboardpanel.getX();
                    int displaytempy = e.getComponent().getY()+gameboardpanel.getY();
                    BufferedImage texturetemp = cont.getlib().gettexture(5).getimage();
                    instansiatedcard cardtemp = ((gameboardtiledisplay)e.getComponent()).getcard();

                    triggercarddisplayanimation trig = new triggercarddisplayanimation(
                                                                                   displaytempx,displaytempy,displaytempw,displaytemph,
                                                                                   panel.getX(),panel.getY(),panel.getWidth(),panel.getHeight(),
                                                                                   texturetemp,cardtemp);
                    trig.trigger(thisref);
                //}
            }else{
                if(selectedcard != null){
                    gameboardtiledisplay temp = (gameboardtiledisplay)e.getSource();
                    if(selectedcard.getcard().canplay(temp.gettile())){
                    
                        //play the card
                        thisref.addtrigger(new triggerplayerplaycard(cont,match,selectedcard,temp.gettile()));
                    
                        //remove card from cursor
                        selecteddisplay = null;
                        selectedcard = null;
                        selectedindex = -1;
                        setCursor(Cursor.getDefaultCursor());
                    
                        //check game board
                        for(int i = 0; i < gbx; i++){
                            for(int j = 0;j < gby; j++){
                                gameboarddisplaylist[i][j].changeinactive(); 
                            }
                        }
                        discardcard.setEnabled(false);
                        thisref.runtriggers(thisref);
                    }
                }
            }
        
        
        }
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {
            ((gameboardtiledisplay)e.getComponent()).mouselocation(e.getX(),e.getY());
        }
        public void mouseExited(MouseEvent e) {
            ((gameboardtiledisplay)e.getComponent()).mouselocation(-1,-1);
        }
        public void mouseClicked(MouseEvent e) {}
    }
    
    private class boardmouseover implements MouseMotionListener{
        public void mouseDragged(MouseEvent e){
            
        }
        
        public void mouseMoved(MouseEvent e) {
            ((gameboardtiledisplay)e.getComponent()).mouselocation(e.getX(),e.getY());
            ((gameboardtiledisplay)e.getSource()).repaint();
        }
    }
    /**********************************
     * playerinfodisplay
     **********************************/
    private class playerinfodisplay extends JPanel{
        
        BufferedImage background;
        boolean player;
        
        public playerinfodisplay(BufferedImage b,boolean p){
            background = b;
            player = p;
        }
        
        public void drawoutlinetext(Graphics2D g2d,String font,String s,int x,int y,Color back,Color front){
            
            Font f = new Font(font, Font.BOLD, 13);
            g2d.setFont(f);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(back);
            
            

            FontRenderContext frc = g2d.getFontRenderContext();
            TextLayout tl = new TextLayout(s, f, frc);
            float sw = (float) tl.getBounds().getWidth();
            AffineTransform transform = new AffineTransform();
            transform.setToTranslation(x,y);
            Shape shape = tl.getOutline(transform);
            Rectangle r = shape.getBounds();
            g2d.setColor(back);
            g2d.setStroke(new BasicStroke(3));
            g2d.draw(shape);
            g2d.setColor(front);
            g2d.drawString(s,x,y);
        }
        
        
        public void paintComponent(Graphics g) {
            int j = 0;
            int i = 0;
            while(j < this.getHeight()){
                i = 0;
                while(i < this.getWidth()){
                    g.drawImage(background, i, j, null); 
                    i+= background.getWidth();
                }
                j+= background.getHeight();
            }
            
            int w = this.getWidth( );
            int h = this.getHeight( );
            Graphics2D g2d = (Graphics2D)g;
            GradientPaint gp = new GradientPaint(0, 0,new Color(255,255,255,0),0, h,new Color(0,0,0,100));
            g2d.setPaint( gp );
            g2d.fillRect( 0, 0, w, h );
            
            Font font = new Font("Arial", Font.PLAIN, 12);
            g2d.setFont(font);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            Color myblue = new Color(0,150,255);
            Color myred = new Color(255,150,0);
            
            Color playercolor;
            if( player == false){
                playercolor = myblue;

                drawoutlinetext(g2d,"Arial",""+match.numplayercardsdeck(),5,20,Color.black,myblue);
                //g2d.drawString(""+match.numplayercardsdeck(),5,20);
                drawoutlinetext(g2d,"Arial",""+match.getplayerpoints(),5,40,Color.black,myblue);
                //g2d.drawString(""+match.getplayerpoints(),5,40);
            }else{
                playercolor = myred;
                
                drawoutlinetext(g2d,"Arial",""+match.numopponentcardsdeck(),5,20,Color.black,myred);
                //g2d.drawString(""+match.numopponentcardsdeck(),5,20);
                drawoutlinetext(g2d,"Arial",""+match.getopponentpoints(),5,40,Color.black,myred);
                //g2d.drawString(""+match.getopponentpoints(),5,40);
            }
            drawoutlinetext(g2d,"Arial","cards in deck",5,10,Color.black,playercolor);
            //g2d.drawString("cards in deck",5,10);
            drawoutlinetext(g2d,"Arial","victory points",5,30,Color.black,playercolor);
            //g2d.drawString("victory points",5,30);
            
        }
    }
    
    /******************************************
     * gameboardtiledisplay
     ******************************************/
    private class gameboardtiledisplay extends JButton{
        
        gametilemodel tile;
        int xloc;
        int yloc;
        
        int tilemouseoverx;
        int tilemouseovery;
        
        public gameboardtiledisplay(int x,int y,gametilemodel t){
            tile = t;
            xloc = x;
            yloc = y;
            tilemouseoverx = -1;
            tilemouseovery = -1;
            this.setBorder(BorderFactory.createLineBorder(Color.black));
        }
        
        public gametilemodel gettile(){
            return tile;
        }
        
        public void mouselocation(int x,int y){
             tilemouseoverx = x;
             tilemouseovery = y;
        }
        
        public instansiatedcard getcard(){
            if(tilemouseoverx == -1){
                return null;
            }else{
                return tile.getcard(tilemouseoverx/(this.getWidth()/tile.numcard()));
            }
        }
        
        public void changeactive(instansiatedcard c){
            if(c.getcard().canplay(tile)){
                this.setBorder(BorderFactory.createLineBorder(Color.yellow));
            }else{
                this.setBorder(BorderFactory.createLineBorder(Color.black));
            }
        }
        
        public void changeinactive(){
            this.setBorder(BorderFactory.createLineBorder(Color.black));
        }
    
        
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            int distance = 3;
            int w = this.getWidth();
            int h = this.getHeight();
            
            Color light = new Color(255,255,255,200);
            Color dark = new Color(0,0,0,200);
            
            //draw tile
            Polygon north = new Polygon();
            north.addPoint(0+2,0+2);
            north.addPoint(w-2,0+2);
            north.addPoint(w-distance-2,distance+2);
            north.addPoint(distance+2,distance+2);
            Polygon east = new Polygon();
            east.addPoint(w-2,0+2);
            east.addPoint(w-2,h-2);
            east.addPoint(w-distance-2,h-distance-2);
            east.addPoint(w-distance-2,distance+2);
            Polygon south = new Polygon();
            south.addPoint(w-2,h-2);
            south.addPoint(0+2,h-2);
            south.addPoint(distance+2,h-distance-2);
            south.addPoint(w-distance-2,h-distance-2);
            Polygon west = new Polygon();
            west.addPoint(0+2,h-2);
            west.addPoint(0+2,0+2);
            west.addPoint(distance+2,distance+2);
            west.addPoint(distance+2,h-distance-2);
            
            g2d.setPaint(dark);
            g2d.fill(north);
        
            g2d.setPaint(light);
            g2d.fill(east);
        
            g2d.setPaint(light);
            g2d.fill(south);
        
            g2d.setPaint(dark);
            g2d.fill(west);
            
            
            Color myblue = new Color(0,150,255);
            Color myred = new Color(255,150,0);
            
            Color mydarkblue = new Color(0,180,255);
            Color mydarkred = new Color(255,180,0);
                
            Color selectedblue  = new Color(0,60,255);
            Color Selectedred = new Color(255,60,0);
            
            //draw cards in tile
            for(int i = 0; i < tile.numcard();i++){
                int imagesizex = 32;
                int imagesizey = 32;
                int displacement = 10;
                int imagelocationx = (this.getWidth()-imagesizex)/2 - displacement*(tile.numcard()/2) + displacement*i;
                int imagelocationy = (this.getHeight()-imagesizey)/2;
               
                instansiatedcard temp = tile.getcard(i);
                
                
                if((((this.getWidth()/tile.numcard())*(i)) < tilemouseoverx)&&(((this.getWidth()/tile.numcard())*(i+1)) > tilemouseoverx)){
                    if(tile.getcard(i).getowner()== true){
                        g.setColor(selectedblue);
                    }else{
                        g.setColor(Selectedred);
                    }
                    g.fillOval(imagelocationx,imagelocationy,imagesizex,imagesizey);
                
                    if(tile.getcard(i).getowner()== true){
                        g.setColor(mydarkblue);
                    }else{
                        g.setColor(mydarkred);
                    }
                    g.drawOval(imagelocationx,imagelocationy,imagesizex,imagesizey);
                
                    g.drawImage(tile.getcard(i).getcard().getsymbol().getimage(),imagelocationx,imagelocationy,imagesizex,imagesizey,null);
                }else{
                    if(tile.getcard(i).getowner()== true){
                        g.setColor(myblue);
                    }else{
                        g.setColor(myred);
                    }
                    g.fillOval(imagelocationx,imagelocationy,imagesizex,imagesizey);
                
                    if(tile.getcard(i).getowner()== true){
                        g.setColor(mydarkblue);
                    }else{
                        g.setColor(mydarkred);
                    }
                    g.drawOval(imagelocationx,imagelocationy,imagesizex,imagesizey);
                
                    g.drawImage(tile.getcard(i).getcard().getsymbol().getimage(),imagelocationx,imagelocationy,imagesizex,imagesizey,null);
                }

            }
        }
        
    }    
}
