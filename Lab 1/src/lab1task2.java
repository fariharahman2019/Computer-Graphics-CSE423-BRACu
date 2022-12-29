import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import java.lang.Math;
import javax.swing.JFrame;

class MyGLEventListener implements GLEventListener {
/**
 * Interface to the GLU library.
 */
private GLU glu;

/**
 * Take care of initialization here.
 */
public void init(GLAutoDrawable gld) {
    GL2 gl = gld.getGL().getGL2();
    glu = new GLU();

    gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    gl.glViewport(-250, -150, 250, 150);
    gl.glMatrixMode(GL2.GL_PROJECTION);
    gl.glLoadIdentity();
    glu.gluOrtho2D(-250.0, 250.0, -150.0, 150.0);
}

public void display(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();

    gl.glClear(GL2.GL_COLOR_BUFFER_BIT);   
    /*
     Here, ID=19101038 is even which is even,
     Displaying E for this ID
     */
    DDA(gl, -50, 80, 50, 80);
    DDA(gl, -50, -80, 50, -80);
    DDA(gl, -50, -80, -50, 80);
    DDA(gl, -50, 0, 20, 0);
    

}

public void reshape(GLAutoDrawable drawable, int x, int y, int width,
        int height) {
}

public void displayChanged(GLAutoDrawable drawable,
        boolean modeChanged, boolean deviceChanged) {
}

public void dispose(GLAutoDrawable arg0)
{
 
}

public void DDA(GL2 gl, float x1, float y1, float x2, float y2) {

    gl.glPointSize(3.0f);
    gl.glColor3d(1,1,1);

    float dx = x2 - x1;
    float dy = y2 - y1;

    if(dx < 0 ){
        dx=dx*-1;
    }
    if(dy < 0){
        dy = dy*-1;
    }
    int R;
    if(dx>dy){
        R = Math.round(dx);
    }
    else{
        R = Math.round(dy);
    }
    float stepUpX = dx/R;
    float stepUpY = dy/R;
    float x = x1;
    float y = y1;
    for (int a = 0, j=0; a<R ; a++,j++){
        if(j==5){
            j=0;
        }
        if(j<2){
            gl.glBegin(GL2.GL_POINTS);
            gl.glVertex2d(x , y);
            gl.glEnd();
        }
        x = x + stepUpX;
        y = y + stepUpY;
    }
}

}
public class lab1task2
{
public static void main(String args[])
{
 //getting the capabilities object of GL2 profile
 final GLProfile profile=GLProfile.get(GLProfile.GL2);
 GLCapabilities capabilities=new GLCapabilities(profile);
 // The canvas
 final GLCanvas glcanvas=new GLCanvas(capabilities);
 MyGLEventListener b=new MyGLEventListener();
 glcanvas.addGLEventListener(b);
 glcanvas.setSize(400, 400);
 //creating frame
 final JFrame frame=new JFrame("Basic frame");
 //adding canvas to frame
 frame.add(glcanvas);
 frame.setSize(640,480);
 frame.setVisible(true);
}
}
