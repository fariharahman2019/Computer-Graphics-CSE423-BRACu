import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;

import java.util.Random;

import javax.swing.JFrame;
public class lab02 implements GLEventListener{
   
	private GLU glu;
   @Override
   public void display(GLAutoDrawable drawable) {
      final GL2 gl = drawable.getGL().getGL2();
      gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
      gl.glPointSize(3.0f);
      gl.glColor3d(0,1,0);
      
      gl.glBegin(GL2.GL_POINTS);
      
     midPodoubleLine(gl, -80, 60, -40, 60);
     midPodoubleLine(gl, -80, 0, -40, 0);
     midPodoubleLine(gl, -80, -60, -40, -60);
     midPodoubleLine(gl, -40, 60, -40, -60);
     
     
     midPodoubleLine(gl, -30, 60, 10, 60);
     midPodoubleLine(gl, -30, -60, 10, -60);
     midPodoubleLine(gl, -30, 60, -30, -60);
     midPodoubleLine(gl, 10, 60, 10, -60);
     midPodoubleLine(gl, -30, 0, 10, 0);
      
      gl.glEnd();
      
   }
   @Override
   public void dispose(GLAutoDrawable arg0) {
      //do nothing
   }
   
   @Override
   public void init(GLAutoDrawable gld) {
	   GL2 gl = gld.getGL().getGL2();
       glu = new GLU();

       gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
       gl.glViewport(-100, -50, -50, 100);
       gl.glMatrixMode(GL2.GL_PROJECTION);
       gl.glLoadIdentity();
       glu.gluOrtho2D(-100.0, 100.0, -100.0, 100.0);



   }

   

   @Override
   public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
      // do nothing
   }
   
   
   public void midPodoubleLine(GL2 gl, double x1, double y1, double x2, double y2 ) {
	   double dx, dy, incE, incNE, d, x, y;
	   x = x1;
	   y = y1;
	   gl.glVertex2d(x,y);
	   double zone = findZone(x1, y1, x2, y2);
	   double[] zone0podoubles = convertToZone0(x1, y1, zone);
	   x1 = zone0podoubles[0];
	   y1 = zone0podoubles[1];
	   zone0podoubles = convertToZone0(x2, y2, zone);
	   x2 = zone0podoubles[0];
	   y2 = zone0podoubles[1];
	   dx = x2-x1;
	   dy = y2-y1;
	   d = 2*dy-dx;
	   incE=2*dy;
	   incNE=(2*dy)-(2*dx);
	   x=x1;
	   y=y1;
	   while(x<x2) {
		   if(d<=0) {
			   d = d + incE;
			   x = x + .001;
			  	   
		   }
		   else {
			   d = d + incNE;
			   x = x + .001;
			   y = y + .001;
		   }
		   double[] originalPodoubles = convertToOriginalZone(x, y, zone);
		   double xoriginal=originalPodoubles[0];
		   double yoriginal=originalPodoubles[1];
//		   System.out.println("x : " +xoriginal );
//		   System.out.println("y : " +yoriginal );
		   gl.glVertex2d(xoriginal,yoriginal);
	   }
	   

   }
   
   
   
   public double findZone (double x1, double y1, double x2, double y2) {
	   double zone =-1;
	   double dx = x2-x1;
	   double dy = y2-y1;
       if (Math.abs(dx) >= Math.abs(dy)) { //zone 0, 3, 4, 7
           if (dy >= 0 && dx >= 0)
               zone= 0;
           else if (dx < 0 && dy >= 0)
               zone= 3;
           else if (dx < 0 && dy < 0)
               zone= 4;
           else zone= 7;
       } else { //zone 1,2,5,6
           if (dy >= 0 && dx >= 0)
               zone= 1;
           else if (dx < 0 && dy >= 0)
               zone= 2;
           else if (dx < 0 && dy < 0)
               zone= 5;
           else zone= 6;
       }
	   
	   return zone;
   }
   
   
   public double[] convertToZone0(double x, double y, double zone) {
	   double[] result = new double[2];
	   if (zone == 0) {
           result[0] = x;
           result[1] = y;
           }
       else if (zone == 1) {
           result[0] = y;
           result[1] = x;
           }
       else if (zone == 2) {
           result[0] = y;
           result[1] = -x;
           }
       else if (zone == 3) {
           result[0] = -x;
           result[1] = y;
           }
       else if (zone == 4) {
           result[0] = -x;
           result[1] = -y;
           }
       else if (zone == 5) {
           result[0] = -y;
           result[1] = -x;
           }
       else if (zone == 6) {
           result[0] = -y;
           result[1] = x;
           }
       else if (zone == 7) {
           result[0] = x;
           result[1] = -y;
           }
	   
	   return result;
   }
   
   
   public double[] convertToOriginalZone(double x, double y, double zone) {
	   double[] result = new double[2];
	   if (zone == 0) {
           result[0] = x;
           result[1] = y;
           }
       else if (zone == 1) {
           result[0] = y;
           result[1] = x;
           }
       else if (zone == 2) {
           result[0] = -y;
           result[1] = x;
           }
       else if (zone == 3) {
           result[0] = -x;
           result[1] = y;
           }
       else if (zone == 4) {
           result[0] = -x;
           result[1] = -y;
           }
       else if (zone == 5) {
           result[0] = -y;
           result[1] = -x;
           }
       else if (zone == 6) {
           result[0] = y;
           result[1] = -x;
           }
       else if (zone == 7) {
           result[0] = x;
           result[1] = -y;
           }
	   
	   
	   return result;
   }
   
   public static void main(String[] args) {
      //getting the capabilities object of GL2 profile
      final GLProfile profile = GLProfile.get(GLProfile.GL2);
      GLCapabilities capabilities = new GLCapabilities(profile);
      // The canvas 
      final GLCanvas glcanvas = new GLCanvas(capabilities);
      lab02 l = new lab02();
      glcanvas.addGLEventListener(l);
      glcanvas.setSize(600, 400);
      //creating frame
      final JFrame frame = new JFrame ("straight Line");
      //adding canvas to frame
      frame.getContentPane().add(glcanvas);
      frame.setSize(frame.getContentPane().getPreferredSize());
      frame.setVisible(true);
   }//end of main
}//end of classimport javax.media.opengl.GL2;

