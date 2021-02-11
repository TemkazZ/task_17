package com.company;

//import javax. swing.*;
//public final class Main implements Runnable {
//    public static void main(String[] args) {
////Swing имеет собственный управляющий поток (т.н. dispatching thread),
////который работает параллельно с основным (в котором выполняется main())
////потоком. Если основной поток закончит работу (метод main завершится),
////поток отвечающий за работу Swing-интерфейса может продолжать свою работу.
////И даже если пользователь закрыл все окна, программа продолжит свою работу
////(до тех пор, пока жив данный поток). Начиная с Java 6, когда все
////компоненты уничтожены, управляющий поток останавливается автоматически.
////Запускаем весь код, работающий в управляющем потоке, даже инициализацию:
//
//        SwingUtilities.invokeLater (new Main());
//    }
//
//    public void run() {
//// Создаем окно с заголовком "Hello, World!"
//        JFrame f = new JFrame ("Hello, World!");
//
//// Ранее практиковалось следующее: создавался listener и регистрировался
//// на экземпляре главного окна, который реагировал на windowClosing()
//// принудительной остановкой виртуальной машины вызовом System.exit()
//// Теперь же есть более "правильный" способ задав реакцию на закрытие окна.
//// Данный способ уничтожает текущее окно, но не останавливает приложение. Тем
//// самым приложение будет работать пока не будут закрыты все окна.
//        f.setDefaultCloseOperation (JFrame. DISPOSE_ON_CLOSE );
//// однако можно задать и так:
//// f.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
//
//// Добавляем на панель окна нередактируемый компонент с текстом.
//// f.getContentPane().add (new JLabel("Hello, World!")); - старый стиль
//        f.add(new JLabel("Hello World"));
//
//// pack() "упаковывает" окно до оптимального размера
//// всех расположенных в нем компонентов.
//        f.pack();
//// Показать окно
//        f.setVisible(true);
//    }}



import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        JFrame fr=new JFrame("Вращение треугольника вокруг своего центра тяжести");
        fr.setPreferredSize( new Dimension(300,300));
        final JPanel pan= new JPanel();
        fr.add(pan);
        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.pack();
        Timer tm= new Timer(500, new ActionListener(){
            int i=0;
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Graphics2D gr=(Graphics2D)pan.getRootPane().getGraphics();
                pan.update(gr);
                GeneralPath path=new GeneralPath();
                path.append(new Polygon(new int []{60,-80,50},new int[]{-60,-50,40},3),true);
                int x=(60-80+50)/3,y=(-60-50+40)/3;
                gr.translate(150, 150);
                AffineTransform tranforms = AffineTransform.getRotateInstance((i++)*0.07, x, y);
                gr.transform(tranforms);
                gr.draw(path);
            }});
        tm.start();
    }
}

//