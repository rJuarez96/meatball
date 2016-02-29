package mx.itesm.meatball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;



/**
 * Created by Roberto on 04/02/2016.
 */
public class PantallaJuego implements Screen {



    private final Principal principal;
    private OrthographicCamera camara;
    private Viewport vista;
    //fondo
    private Texture texturaFondo;
    private Sprite spriteFondo;

    //dibujar
    private SpriteBatch batch;
    //hoyos 9 ,3x3
  //  private Array<Objeto> hoyos;
    //private Texture texturaHoyo;
    //topos 9 3x3
    //private Array<Objeto> topos;
    private Texture texturaAlbondiga;
    private Sprite spriteAlbondiga;
    //marcador (contador)
   //private  int marcador;
    private Texto huehu;

    //sonidos
    private Sound efectoGolpe;
    private Music musicaFondo;

    public PantallaJuego(Principal principal) {

        this.principal=principal;
       //marcador=0;
        huehu= new Texto();
    }
    @Override
    public void show() {
        //se ejecuta uando se muestra
        camara=new OrthographicCamera(Principal.anchoMundo,Principal.altoMundo);
        camara.position.set(Principal.anchoMundo/2,Principal.altoMundo/2,0);
        vista= new StretchViewport(Principal.anchoMundo,Principal.altoMundo,camara);
        batch= new SpriteBatch();

        //fondo
        cargaTexturasSprites();

        crearHoyos();
        crearTopos();
        //efectoGolpe=Gdx.audio.newSound(Gdx.files.internal("golpe.wav"));
        //musicaFondo=Gdx.audio.newMusic(Gdx.files.internal("music.wav"));
        //musicaFondo.setLooping(true);
        //musicaFondo.play();
    }

    private void crearTopos() {
        /*topos= new Array<Objeto>(9);
        for (int i=0;i<9;i++){
            Objeto nuevo = new Objeto(texturaTopo);
            topos.add(nuevo);
            float x=(i%3)*280+250;
            float y=(i/3)*150+230;
            nuevo.setPosicion(x, y);
        }*/
    }

    private void crearHoyos() {
       /* hoyos= new Array<Objeto>(9);
        for (int i=0; i<9;i++){
            Objeto nuevo=new Objeto(texturaHoyo);
            hoyos.add(nuevo);
            //cambiar la posicion de los hoyos
            float x=(i%3)*280+200;
            float y=(i/3)*150+200;
            nuevo.setPosicion(x,y);
        }*/
    }

    private void cargaTexturasSprites() {
        texturaFondo=new Texture(Gdx.files.internal("2sa2.jpg"));
        spriteFondo=new Sprite(texturaFondo);
       texturaAlbondiga= new Texture(Gdx.files.internal("Albondiga.png"));
        spriteAlbondiga=new Sprite(texturaAlbondiga);
        spriteAlbondiga.setPosition(80,10);
        spriteAlbondiga.setSize(120,120);


    }

    @Override
    public void render(float delta) {
        //leer
        leerEntrada();
        // actualizar objetos
       /* for (Objeto topo :
                topos) {
            topo.actualizar();
        }*/
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Proyectar camar
        batch.setProjectionMatrix(camara.combined);
        //dibujamos
        batch.begin();
        spriteFondo.draw(batch);
        spriteAlbondiga.draw(batch);


       /* for (Objeto hoyo :
                hoyos) {
            hoyo.render(batch);

            ;
        }
        for (Objeto topo :
                topos) {
            topo.render(batch);

            ;
        }*/
        //huehu.mostrarMensaje("Puntos: " + marcador, principal.anchoMundo / 2, principal.altoMundo * 0.95f, batch);
        batch.end();



    }
    private void leerEntrada() {
        if (Gdx.input.justTouched()==true) {
            Vector3 coordenadas = new Vector3();
            coordenadas.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camara.unproject(coordenadas); // Transforma las coord
            float touchX = coordenadas.x;
            float touchY = coordenadas.y;


            if (touchX>=0 && touchX<=100 && touchY>=0 && touchY<=100){
                principal.setScreen(new pantallaMenu(principal));
            }
            // CAMBIAR
            // Recorrer los 9 topos
           /* for (Objeto topo :
                    topos) {
                if ( topo.detectarTouch(touchX, touchY)==true ) {
                    marcador += 1;
                    // efectoGolpe.play();


                }
            }*/
        }
    }
    @Override
    public void resize(int width, int height) {
        vista.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        //cualdo la pantalla sale de memoria
        //Libera recursos
        texturaFondo.dispose();//regresamos la memoria
        texturaAlbondiga.dispose();
       /* texturaTopo.dispose();
        texturaHoyo.dispose();
*/

    }
}
