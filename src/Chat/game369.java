package Chat;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class game369 extends JFrame{
	game369(){
        this.setTitle("369 GAME");
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new GamePanel());//��ü�� ������ ���ÿ� �����ӿ� �߰�
        this.setLocationRelativeTo(null);
        this.setSize(310, 250);
        this.setVisible(true);
    }
	
	class GamePanel extends JPanel{
	    TimerThread th;//���� ī��Ʈ�� ���� ������ ����
	    JLabel card = new JLabel();//���� ī��Ʈ�� ������ ���̺� ����
	    JButton start = new JButton("Start");//���� ��ư ����
	    int n=1;//ī��Ʈ ������ ����
	    boolean singleClicked=false;//Ŭ�� ����
	    boolean doubleClicked=false;//����Ŭ�� ����
	    
	    GamePanel(){
	        //���� ī��Ʈ�� ������ ���̺��� �гο� �߰�
	        this.setLayout(null);
	        card.setOpaque(true);
	        card.setBackground(Color.orange);
	        card.setFont(new Font("Arial",Font.ITALIC,30));
	        card.setHorizontalAlignment(JLabel.CENTER);
	        card.setText(Integer.toString(n));
	        card.setSize(100, 50);
	        card.setLocation(100, 50);
	        card.addMouseListener(new MouseListener(){
	            public void mouseClicked(MouseEvent me) {
	            }

	            public void mousePressed(MouseEvent me) {
	                if(me.getClickCount()>=2){//���� Ŭ���̻� �Ͽ��� �� ����Ŭ������ �ν�
	                    doubleClicked=true;
	                    singleClicked=false;
	                }
	                else{//�ѹ� Ŭ������ ��
	                    singleClicked=true;
	                }
	                //���콺�� ������ �� ���̺� ��ü�� �ҽ��� ���� ������ �ʷ����� ����
	                JLabel card = (JLabel)me.getSource();
	                card.setBackground(Color.yellow);
	                }

	            public void mouseReleased(MouseEvent me) {
	                //���콺�� ������ �� ���̺� ��ü�� �ҽ��� ���� ������ �������� ����
	                JLabel card = (JLabel)me.getSource();
	                card.setBackground(Color.orange);
	            }

	            public void mouseEntered(MouseEvent me) {}

	            public void mouseExited(MouseEvent me) {}
	        });
	        this.add(card);
	        
	        //��ư�� �гο� �߰�
	        start.setLocation(100,150);
	        start.setSize(100, 30);
	        start.addActionListener(new ActionListener(){
	            @Override
	            public void actionPerformed(ActionEvent ae) {
	                //��ưŬ���� �ʱ⼳���� ������ ��ü ������ ����
	                n=1;
	                card.setText(Integer.toString(n));
	                singleClicked=false;
	                doubleClicked=false;
	                th = new TimerThread();
	                th.start();
	                
	                //���� �߿��� ��ư Ŭ���� ���ϰ� ��
	                JButton b = (JButton)ae.getSource();
	                b.setEnabled(false);
	            }
	        });
	        this.add(start);
	    }
	    
	    synchronized void setSingleClicked(boolean b){//�ѹ� Ŭ��
	        singleClicked = b;
	    }
	    
	    synchronized void setDoubleClicked(boolean b){//���� Ŭ��
	        doubleClicked = b;
	    }
	    
	    class TimerThread extends Thread{
	        public void run(){
	            while(true){
	                try{
	                    sleep(700);//0.7�ʸ���

	                    int d1=n%10;//���� �ڸ�
	                    int d2=n/10;//���� �ڸ�

	                    if((d1==3||d1==6||d1==9)&&(d2==3||d2==6||d2==9)){//����Ŭ���� �ؾ��� ��Ȳ
	                        if(doubleClicked==true){//����Ŭ�� ������ �ٽ� �������� �ٲ���
	                            setDoubleClicked(false);
	                        }
	                        else{//���н� �޼��� ���
	                            card.setText("FAIL...");
	                            break;
	                        }
	                    }
	                    else if(d1==3||d1==6||d1==9||d2==3||d2==6||d2==9){//�ѹ�Ŭ���� �ؾ��� ��Ȳ
	                        if(singleClicked==true){//������
	                            setSingleClicked(false);
	                        }
	                        else{//���н�
	                            card.setText("FAIL...");
	                        }
	                    }
	                    else{//�ƹ��͵� Ŭ������ ���ƾ��� ��Ȳ
	                        if(singleClicked==true||doubleClicked==true){//Ŭ���� ����
	                            card.setText("FAIL...");
	                            break;
	                        }
	                    }
	                    
	                    n++;//ī��Ʈ �� 1�� ����
	                    if(n==30){//30���� ���� �¸�
	                        card.setText("WIN!!");
	                        break;
	                    }
	                    else{
	                        card.setText(Integer.toString(n));
	                    }
	                }
	                catch(Exception e){ return; }
	            }
	            start.setEnabled(true);//������ ������ ��ư Ȱ��ȭ
	        }
	    }
	}
	
}