package com.orizevtechnologies.quiz_app;

import androidx.appcompat.app.AppCompatActivity;
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

public class respuesta_nivel1 extends AppCompatActivity {

    private Button btnContinuar;
    private LinearLayout niveles_completados;
    private LinearLayout rpta_correcta_layout_oro;
    private int actual_lvl = 0;
    private int max_lvl = 0;
    private TextView respuesta_texto;
    private ImageView respuesta_img;
    private int valor_volumen = 0;
    private SoundPool sp;
    private int sound_abrir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respuesta_nivel1);
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
        Intent intent = new Intent(respuesta_nivel1.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition( R.anim.slide_left_in, R.anim.slide_left_out);
    }

    private void init_view() {
        btnContinuar = findViewById(R.id.btnContinuar);
        respuesta_texto = findViewById(R.id.respuesta_texto);
        respuesta_img = findViewById(R.id.respuesta_img);
        niveles_completados = findViewById(R.id.niveles_completados);
        rpta_correcta_layout_oro = findViewById(R.id.rpta_correcta_layout_oro);
    }

    private void click_button() {
        btnContinuar.setOnClickListener(view -> {
            detalle_sonido();
            if (actual_lvl == ultimo_nivel) {
                reiniciar_nivel_actual();
                Intent intent = new Intent(respuesta_nivel1.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition( R.anim.slide_left_in, R.anim.slide_left_out);
            } else {
                Intent intent = new Intent(respuesta_nivel1.this, Nivel1.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }

    private void nivel_recompensa() {
        SharedPreferences sp2 = this.getSharedPreferences("shared", Context.MODE_PRIVATE);
        actual_lvl = sp2.getInt(getString(R.string.nivel_actual), 1);
        max_lvl = sp2.getInt(getString(R.string.nivel_maximo), 1);
        valor_volumen = sp2.getInt(getString(R.string.estado_volumen), 1);

        sp = new SoundPool.Builder().setMaxStreams(10).build();
        sound_abrir = sp.load(this, R.raw.abrir,1);
    }

    private  void reiniciar_nivel_actual() {
        SharedPreferences sp = this.getSharedPreferences("shared", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        int lvl_actualizado = 1;
        editor.putInt(getString(R.string.nivel_actual), lvl_actualizado);
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
    private final int ultimo_nivel = 61; //Ultimo nivel + 1

    @SuppressLint("SetTextI18n")
    private void cambiar_texto_img() {
        switch (actual_lvl) {
            case 2:
                respuesta_texto.setText("Bounty Hunter");
                respuesta_img.setImageResource(R.drawable.nivel1_principal);
                break;
            case 3:
                respuesta_texto.setText("Doom");
                respuesta_img.setImageResource(R.drawable.nivel2_principal);
                break;
            case 4:
                respuesta_texto.setText("Pudge");
                respuesta_img.setImageResource(R.drawable.nivel3_principal);
                break;
            case 5:
                respuesta_texto.setText("Sniper");
                respuesta_img.setImageResource(R.drawable.nivel4_principal);
                break;
            case 6:
                respuesta_texto.setText("Tiny");
                respuesta_img.setImageResource(R.drawable.nivel5_principal);
                break;
            case 7:
                respuesta_texto.setText("Kunkka");
                respuesta_img.setImageResource(R.drawable.nivel6_principal);
                break;
            case 8:
                respuesta_texto.setText("Faceless Void");
                respuesta_img.setImageResource(R.drawable.nivel7_principal);
                break;
            case 9:
                respuesta_texto.setText("Broodmother");
                respuesta_img.setImageResource(R.drawable.nivel8_principal);
                break;
            case 10:
                respuesta_texto.setText("Crystal Maiden");
                respuesta_img.setImageResource(R.drawable.nivel9_principal);
                break;
            case 11:
                respuesta_texto.setText("Juggernaut");
                respuesta_img.setImageResource(R.drawable.nivel10_principal);
                break;
            case 12:
                respuesta_texto.setText("Meepo");
                respuesta_img.setImageResource(R.drawable.nivel11_principal);
                break;
            case 13:
                respuesta_texto.setText("Gyrocopter");
                respuesta_img.setImageResource(R.drawable.nivel12_principal);
                break;
            case 14:
                respuesta_texto.setText("Lion");
                respuesta_img.setImageResource(R.drawable.nivel13_principal);
                break;
            case 15:
                respuesta_texto.setText("Riki");
                respuesta_img.setImageResource(R.drawable.nivel14_principal);
                break;
            case 16:
                respuesta_texto.setText("Skywrath Mage");
                respuesta_img.setImageResource(R.drawable.nivel15_principal);
                break;
            case 17:
                respuesta_texto.setText("Shadow Shaman");
                respuesta_img.setImageResource(R.drawable.nivel16_principal);
                break;
            case 18:
                respuesta_texto.setText("Clinkz");
                respuesta_img.setImageResource(R.drawable.nivel17_principal);
                break;
            case 19:
                respuesta_texto.setText("Undying");
                respuesta_img.setImageResource(R.drawable.nivel18_principal);
                break;
            case 20:
                respuesta_texto.setText("Necrophos");
                respuesta_img.setImageResource(R.drawable.nivel19_principal);
                break;
            case 21:
                respuesta_texto.setText("Mirana");
                respuesta_img.setImageResource(R.drawable.nivel20_principal);
                break;
            case 22:
                respuesta_texto.setText("Bane");
                respuesta_img.setImageResource(R.drawable.nivel21_principal);
                break;
            case 23:
                respuesta_texto.setText("Enigma");
                respuesta_img.setImageResource(R.drawable.nivel22_principal);
                break;
            case 24:
                respuesta_texto.setText("Enchantress");
                respuesta_img.setImageResource(R.drawable.nivel23_principal);
                break;
            case 25:
                respuesta_texto.setText("Wraith King");
                respuesta_img.setImageResource(R.drawable.nivel24_principal);
                break;
            case 26:
                respuesta_texto.setText("Lone Druid");
                respuesta_img.setImageResource(R.drawable.nivel25_principal);
                break;
            case 27:
                respuesta_texto.setText("Queen of Pain");
                respuesta_img.setImageResource(R.drawable.nivel26_principal);
                break;
            case 28:
                respuesta_texto.setText("Axe");
                respuesta_img.setImageResource(R.drawable.nivel27_principal);
                break;
            case 29:
                respuesta_texto.setText("Medusa");
                respuesta_img.setImageResource(R.drawable.nivel28_principal);
                break;
            case 30:
                respuesta_texto.setText("Techies");
                respuesta_img.setImageResource(R.drawable.nivel29_principal);
                break;
            case 31:
                respuesta_texto.setText("Timbersaw");
                respuesta_img.setImageResource(R.drawable.nivel30_principal);
                break;
            case 32:
                respuesta_texto.setText("Lycan");
                respuesta_img.setImageResource(R.drawable.nivel31_principal);
                break;
            case 33:
                respuesta_texto.setText("Lifestealer");
                respuesta_img.setImageResource(R.drawable.nivel32_principal);
                break;
            case 34:
                respuesta_texto.setText("Mars");
                respuesta_img.setImageResource(R.drawable.nivel33_principal);
                break;
            case 35:
                respuesta_texto.setText("Lina");
                respuesta_img.setImageResource(R.drawable.nivel34_principal);
                break;
            case 36:
                respuesta_texto.setText("Monkey King");
                respuesta_img.setImageResource(R.drawable.nivel35_principal);
                break;
            case 37:
                respuesta_texto.setText("Rubick");
                respuesta_img.setImageResource(R.drawable.nivel36_principal);
                break;
            case 38:
                respuesta_texto.setText("Tidehunter");
                respuesta_img.setImageResource(R.drawable.nivel37_principal);
                break;
            case 39:
                respuesta_texto.setText("Windranger");
                respuesta_img.setImageResource(R.drawable.nivel38_principal);
                break;
            case 40:
                respuesta_texto.setText("Tusk");
                respuesta_img.setImageResource(R.drawable.nivel39_principal);
                break;
            case 41:
                respuesta_texto.setText("Pangolier");
                respuesta_img.setImageResource(R.drawable.nivel40_principal);
                break;
            case 42:
                respuesta_texto.setText("Sand King");
                respuesta_img.setImageResource(R.drawable.nivel41_principal);
                break;
            case 43:
                respuesta_texto.setText("Hoodwink");
                respuesta_img.setImageResource(R.drawable.nivel42_principal);
                break;
            case 44:
                respuesta_texto.setText("Elder Titan");
                respuesta_img.setImageResource(R.drawable.nivel43_principal);
                break;
            case 45:
                respuesta_texto.setText("Clockwerk");
                respuesta_img.setImageResource(R.drawable.nivel44_principal);
                break;
            case 46:
                respuesta_texto.setText("Dark Seer");
                respuesta_img.setImageResource(R.drawable.nivel45_principal);
                break;
            case 47:
                respuesta_texto.setText("Abbadon");
                respuesta_img.setImageResource(R.drawable.nivel46_principal);
                break;
            case 48:
                respuesta_texto.setText("Phantom Lancer");
                respuesta_img.setImageResource(R.drawable.nivel47_principal);
                break;
            case 49:
                respuesta_texto.setText("Razor");
                respuesta_img.setImageResource(R.drawable.nivel48_principal);
                break;
            case 50:
                respuesta_texto.setText("Vengeful Spirit");
                respuesta_img.setImageResource(R.drawable.nivel49_principal);
                break;
            case 51:
                respuesta_texto.setText("Ursa");
                respuesta_img.setImageResource(R.drawable.nivel50_principal);
                break;
            case 52:
                respuesta_texto.setText("Troll Warlord");
                respuesta_img.setImageResource(R.drawable.nivel51_principal);
                break;
            case 53:
                respuesta_texto.setText("Phoenix");
                respuesta_img.setImageResource(R.drawable.nivel52_principal);
                break;
            case 54:
                respuesta_texto.setText("Chen");
                respuesta_img.setImageResource(R.drawable.nivel53_principal);
                break;
            case 55:
                respuesta_texto.setText("Luna");
                respuesta_img.setImageResource(R.drawable.nivel54_principal);
                break;
            case 56:
                respuesta_texto.setText("Alchemist");
                respuesta_img.setImageResource(R.drawable.nivel55_principal);
                break;
            case 57:
                respuesta_texto.setText("Dark Willow");
                respuesta_img.setImageResource(R.drawable.nivel56_principal);
                break;
            case 58:
                respuesta_texto.setText("Batrider");
                respuesta_img.setImageResource(R.drawable.nivel57_principal);
                break;
            case 59:
                respuesta_texto.setText("Morphling");
                respuesta_img.setImageResource(R.drawable.nivel58_principal);
                break;
            case 60:
                respuesta_texto.setText("Bloodseeker");
                respuesta_img.setImageResource(R.drawable.nivel59_principal);
                break;
            case 61:
                respuesta_texto.setText("Beastmaster");
                respuesta_img.setImageResource(R.drawable.nivel60_principal);
                break;
        }
    }

}