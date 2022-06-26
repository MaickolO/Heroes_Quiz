package com.orizevtechnologies.quiz_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class respuesta_nivel2 extends AppCompatActivity {

    private Button btnContinuar;
    private LinearLayout niveles_completados;
    private LinearLayout rpta_correcta_layout_oro;
    private int actual_lvl = 0;
    private int max_lvl = 0;
    private TextView respuesta_texto;
    private ImageView respuesta_img1;
    private ImageView respuesta_img2;
    private int valor_volumen = 0;
    private SoundPool sp;
    private int sound_abrir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respuesta_nivel2);
            Show_Hide_SystemUI();
            init_view();
            nivel_recompensa();
            click_button();
            cambiar_texto_img();
            datos_agregados_segun_condicional();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (actual_lvl == ultimo_nivel) {
            reiniciar_nivel_actual();
        }
    }

    protected void onPause() {
        super.onPause();
        if (actual_lvl % 10 == 0) {
            if (sp != null ) {
                sp.release();
            }
        }
    }

    protected void onResume() {
        super.onResume();
        if (sp == null) {
            sp = new SoundPool.Builder().setMaxStreams(10).build();
            sound_abrir = sp.load(this, R.raw.abrir,1);
        }
    }

    @Override
    public void onBackPressed() {
        detalle_sonido();
        if (actual_lvl == ultimo_nivel) {
            reiniciar_nivel_actual();
        }
        Intent intent = new Intent(respuesta_nivel2.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition( R.anim.slide_left_in, R.anim.slide_left_out);
    }

    private void init_view() {
        btnContinuar = findViewById(R.id.btnContinuar);
        respuesta_texto = findViewById(R.id.respuesta_texto);
        respuesta_img1 = findViewById(R.id.nivel2_rpta_img1);
        respuesta_img2 = findViewById(R.id.nivel2_rpta_img2);
        niveles_completados = findViewById(R.id.niveles_completados);
        rpta_correcta_layout_oro = findViewById(R.id.rpta_correcta_layout_oro);
    }

    private void click_button() {
        btnContinuar.setOnClickListener(view -> {
            detalle_sonido();
            if (actual_lvl == ultimo_nivel) {
                reiniciar_nivel_actual();
                Intent intent = new Intent(respuesta_nivel2.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition( R.anim.slide_left_in, R.anim.slide_left_out);
            } else {
                Intent intent = new Intent(respuesta_nivel2.this, Nivel2.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }

    private void nivel_recompensa() {
        SharedPreferences sp2 = this.getSharedPreferences("shared", Context.MODE_PRIVATE);
        actual_lvl = sp2.getInt(getString(R.string.nivel_actual_2), 1);
        max_lvl = sp2.getInt(getString(R.string.nivel_maximo_2), 1);
        valor_volumen = sp2.getInt(getString(R.string.estado_volumen), 1);

        sp = new SoundPool.Builder().setMaxStreams(10).build();
        sound_abrir = sp.load(this, R.raw.abrir,1);
    }

    private  void reiniciar_nivel_actual() {
        SharedPreferences sp = this.getSharedPreferences("shared", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        int lvl_actualizado = 1;
        editor.putInt(getString(R.string.nivel_actual_2), lvl_actualizado);
        editor.apply();
    }

    private void detalle_sonido() {
        if (valor_volumen==1) {
            sp.play(sound_abrir, 1,1,1,0,1);
        }
    }

    private void Show_Hide_SystemUI() {
        View decorView = this.getWindow().getDecorView();
        int newUiOptions = decorView.getSystemUiVisibility();
        newUiOptions |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
        newUiOptions |= View.SYSTEM_UI_FLAG_FULLSCREEN;
        newUiOptions |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        newUiOptions |= View.SYSTEM_UI_FLAG_IMMERSIVE;
        newUiOptions |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(newUiOptions);
    }

    @SuppressLint("SetTextI18n")
    private void datos_agregados_segun_condicional() {
        if (max_lvl == ultimo_nivel) {
            rpta_correcta_layout_oro.setVisibility(View.GONE);
        }

        if (actual_lvl == ultimo_nivel) {
            //Mostrar en el último nivel
            niveles_completados.setVisibility(View.VISIBLE);
            btnContinuar.setText("Volver");
        }
    }


    /* MODIFICAR AL AGREGAR NUEVOS NIVELES (nivel = 60) */
    private final int ultimo_nivel = 31; //Ultimo nivel + 1

    @SuppressLint("SetTextI18n")
    private void cambiar_texto_img() {
        switch (actual_lvl) {
            case 2:
                respuesta_texto.setText("Lifestealer");
                respuesta_img1.setImageResource(R.drawable.lvlb_nivel1_principal1);
                respuesta_img2.setImageResource(R.drawable.lvlb_nivel1_principal2);
                break;
            case 3:
                respuesta_texto.setText("Windranger");
                respuesta_img1.setImageResource(R.drawable.lvlb_nivel2_principal1);
                respuesta_img2.setImageResource(R.drawable.lvlb_nivel2_principal2);
                break;
            case 4:
                respuesta_texto.setText("Bounty Hunter");
                respuesta_img1.setImageResource(R.drawable.lvlb_nivel3_principal1);
                respuesta_img2.setImageResource(R.drawable.lvlb_nivel3_principal2);
                break;
            case 5:
                respuesta_texto.setText("Earth Spirit");
                respuesta_img1.setImageResource(R.drawable.lvlb_nivel4_principal1);
                respuesta_img2.setImageResource(R.drawable.lvlb_nivel4_principal2);
                break;
            case 6:
                respuesta_texto.setText("Gyrocopter");
                respuesta_img1.setImageResource(R.drawable.lvlb_nivel5_principal1);
                respuesta_img2.setImageResource(R.drawable.lvlb_nivel5_principal2);
                break;
            case 7:
                respuesta_texto.setText("Beastmaster");
                respuesta_img1.setImageResource(R.drawable.lvlb_nivel6_principal1);
                respuesta_img2.setImageResource(R.drawable.lvlb_nivel6_principal2);
                break;
            case 8:
                respuesta_texto.setText("Invoker");
                respuesta_img1.setImageResource(R.drawable.lvlb_nivel7_principal1);
                respuesta_img2.setImageResource(R.drawable.lvlb_nivel7_principal2);
                break;
            case 9:
                respuesta_texto.setText("Night Stalker");
                respuesta_img1.setImageResource(R.drawable.lvlb_nivel8_principal1);
                respuesta_img2.setImageResource(R.drawable.lvlb_nivel8_principal2);
                break;
            case 10:
                respuesta_texto.setText("Ogre Magi");
                respuesta_img1.setImageResource(R.drawable.lvlb_nivel9_principal1);
                respuesta_img2.setImageResource(R.drawable.lvlb_nivel9_principal2);
                break;
            case 11:
                respuesta_texto.setText("Leshrac");
                respuesta_img1.setImageResource(R.drawable.lvlb_nivel10_principal1);
                respuesta_img2.setImageResource(R.drawable.lvlb_nivel10_principal2);
                break;
            case 12:
                respuesta_texto.setText("Huskar");
                respuesta_img1.setImageResource(R.drawable.lvlb_nivel11_principal1);
                respuesta_img2.setImageResource(R.drawable.lvlb_nivel11_principal2);
                break;
            case 13:
                respuesta_texto.setText("Earthshaker");
                respuesta_img1.setImageResource(R.drawable.lvlb_nivel12_principal1);
                respuesta_img2.setImageResource(R.drawable.lvlb_nivel12_principal2);
                break;
            case 14:
                respuesta_texto.setText("Dazzle");
                respuesta_img1.setImageResource(R.drawable.lvlb_nivel13_principal1);
                respuesta_img2.setImageResource(R.drawable.lvlb_nivel13_principal2);
                break;
            case 15:
                respuesta_texto.setText("Morphling");
                respuesta_img1.setImageResource(R.drawable.lvlb_nivel14_principal1);
                respuesta_img2.setImageResource(R.drawable.lvlb_nivel14_principal2);
                break;
            case 16:
                respuesta_texto.setText("Phantom Lancer");
                respuesta_img1.setImageResource(R.drawable.lvlb_nivel15_principal1);
                respuesta_img2.setImageResource(R.drawable.lvlb_nivel15_principal2);
                break;
            case 17:
                respuesta_texto.setText("Clockwerk");
                respuesta_img1.setImageResource(R.drawable.lvlb_nivel16_principal1);
                respuesta_img2.setImageResource(R.drawable.lvlb_nivel16_principal2);
                break;
            case 18:
                respuesta_texto.setText("Zeus");
                respuesta_img1.setImageResource(R.drawable.lvlb_nivel17_principal1);
                respuesta_img2.setImageResource(R.drawable.lvlb_nivel17_principal2);
                break;
            case 19:
                respuesta_texto.setText("Medusa");
                respuesta_img1.setImageResource(R.drawable.lvlb_nivel18_principal1);
                respuesta_img2.setImageResource(R.drawable.lvlb_nivel18_principal2);
                break;
            case 20:
                respuesta_texto.setText("Jakiro");
                respuesta_img1.setImageResource(R.drawable.lvlb_nivel19_principal1);
                respuesta_img2.setImageResource(R.drawable.lvlb_nivel19_principal2);
                break;
            case 21:
                respuesta_texto.setText("Bristleback");
                respuesta_img1.setImageResource(R.drawable.lvlb_nivel20_principal1);
                respuesta_img2.setImageResource(R.drawable.lvlb_nivel20_principal2);
                break;
            case 22:
                respuesta_texto.setText("Faceless Void");
                respuesta_img1.setImageResource(R.drawable.lvlb_nivel21_principal1);
                respuesta_img2.setImageResource(R.drawable.lvlb_nivel21_principal2);
                break;
            case 23:
                respuesta_texto.setText("Omniknight");
                respuesta_img1.setImageResource(R.drawable.lvlb_nivel22_principal1);
                respuesta_img2.setImageResource(R.drawable.lvlb_nivel22_principal2);
                break;
            case 24:
                respuesta_texto.setText("Razor");
                respuesta_img1.setImageResource(R.drawable.lvlb_nivel23_principal1);
                respuesta_img2.setImageResource(R.drawable.lvlb_nivel23_principal2);
                break;
            case 25:
                respuesta_texto.setText("Vengeful Spirit");
                respuesta_img1.setImageResource(R.drawable.lvlb_nivel24_principal1);
                respuesta_img2.setImageResource(R.drawable.lvlb_nivel24_principal2);
                break;
            case 26:
                respuesta_texto.setText("Elder Titan");
                respuesta_img1.setImageResource(R.drawable.lvlb_nivel25_principal1);
                respuesta_img2.setImageResource(R.drawable.lvlb_nivel25_principal2);
                break;
            case 27:
                respuesta_texto.setText("Bane");
                respuesta_img1.setImageResource(R.drawable.lvlb_nivel26_principal1);
                respuesta_img2.setImageResource(R.drawable.lvlb_nivel26_principal2);
                break;
            case 28:
                respuesta_texto.setText("Shadow Demon");
                respuesta_img1.setImageResource(R.drawable.lvlb_nivel27_principal1);
                respuesta_img2.setImageResource(R.drawable.lvlb_nivel27_principal2);
                break;
            case 29:
                respuesta_texto.setText("Warlock");
                respuesta_img1.setImageResource(R.drawable.lvlb_nivel28_principal1);
                respuesta_img2.setImageResource(R.drawable.lvlb_nivel28_principal2);
                break;
            case 30:
                respuesta_texto.setText("Sven");
                respuesta_img1.setImageResource(R.drawable.lvlb_nivel29_principal1);
                respuesta_img2.setImageResource(R.drawable.lvlb_nivel29_principal2);
                break;
            case 31:
                respuesta_texto.setText("Anti Mage");
                respuesta_img1.setImageResource(R.drawable.lvlb_nivel30_principal1);
                respuesta_img2.setImageResource(R.drawable.lvlb_nivel30_principal2);
                break;
        }
    }

}