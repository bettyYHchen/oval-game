import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Scanner;

public class MovingGame extends JPanel{
	 public static BufferedImage background;
	    public static BufferedImage redCircle;
	    public static BufferedImage blueCircle;
	    public static final int WIDTH = 500;
	    public static final int HEIGHT = 500;
	    public static final int START=0;
	    public static final int PAUSE=2;
	    public static final int RUNNING=1;
	    protected int state = START;
	    protected Oval player1 = new Oval();
	    protected Oval player2 = new Oval();

	    static {
	        try {
	            background = ImageIO.read(MovingGame.class.getResource("background.png"));
	            redCircle = ImageIO.read(MovingGame.class.getResource("redCircle.png"));
	            blueCircle = ImageIO.read(MovingGame.class.getResource("blueCircle.png"));

	        }
	        catch(Exception e) {
	            System.out.println("Image Loading Error!");
	        }


	    }

	    public class Oval {
	        protected int x;
	        protected int y;
	        protected int width;
	        protected int height;
	        protected BufferedImage image;

	        public void step() {
	            Random randomGenerator = new Random();
	            int speed = (randomGenerator.nextInt(5) + 1)*10;
	            x = x + speed;
	        }

	        public void step(int speed) {
	            x = x + speed;
	        }

	        public void moveTo(int x, int y) {
	            this.x = x - this.width/2;
	            this.y = y - this.height/2;
	        }

	        public Boolean outOfBound() {
	            if (x > MovingGame.WIDTH) { return true; }
	            else {return false;}
	        }

	        public Boolean reachEnd() {
	            if ( x == MovingGame.WIDTH) { return true;
	            } else { return false;}
	        }

	        public int getX() {
	            return x;
	        }

	        public void setX(int x) {
	            this.x = x;
	        }

	        public int getY() {
	            return y;
	        }

	        public void setY(int y) {
	            this.y = y;
	        }

	        public int getWidth() {
	            return width;
	        }

	        public void setWidth(int width) {
	            this.width = width;
	        }

	        public int getHeight() {
	            return height;
	        }

	        public void setHeight(int height) {
	            this.height = height;
	        }

	        public BufferedImage getImage() {
	            return image;
	        }

	        public void setImage(BufferedImage image) {
	            this.image = image;
	        }
	    }

	    public void Action() {
	        // randomly create x coordinate
	        Random randomGenerator = new Random();
	        int x = randomGenerator.nextInt(WIDTH/2) + 1;
	        int y1 = 100;
	        int y2 = 300;
	        player1.setImage(redCircle);
	        player2.setImage(blueCircle);
	        player1.moveTo(x,y1);
	        player2.moveTo(x,y2);
	        repaint();
	        Scanner reader = new Scanner(System.in);
	        int n = 1;
	        while (!player1.outOfBound() && !player2.outOfBound() && !player1.reachEnd() && !player2.reachEnd() && n!=0) {
	            player1.step();
	            player2.step();
	            repaint();
	            System.out.println("Enter any number not zero to continue: ");
	            n = reader.nextInt();
	        }
	        if (player1.outOfBound()) {
	            if (!player2.outOfBound()) {
	                System.out.println("player 2 wins!");
	            }else {
	                System.out.println("tie");
	            }
	        }

	        if (player1.reachEnd()) {
	            System.out.println("player 1 wins!");
	        }

	        if (player2.reachEnd()) {
	            System.out.println("player 2 wins!");
	        }
	        repaint();



	    }

	    public void paint(Graphics g) {
	        g.drawImage(background, 0, 0,null);
	        g.drawImage(player1.image, player1.x, player1.y,null);
	        g.drawImage(player2.image, player2.x, player2.y,null);

	    }

	    public static void main(String[] args) {
	        MovingGame game = new MovingGame();
	        JFrame frame = new JFrame();
	        frame.add(game);
	        frame.setSize(WIDTH,HEIGHT);
	        frame.setAlwaysOnTop(true);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setLocationRelativeTo(null);
	        frame.setVisible(true);
	        game.Action();
	    }

}
