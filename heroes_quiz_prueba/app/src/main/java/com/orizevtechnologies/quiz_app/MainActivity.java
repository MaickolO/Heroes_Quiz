package com.orizevtechnologies.quiz_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LottieAnimationView btn_main_config;
    private Button btnInicio_nivel1;
    private int actual_lvl = 0;
    private Button btn_main_coins;
    private int gold = 0;
    private int valor_volumen = 0;
    //Nivel 1
    private ConstraintLayout parent_img_nivel1;
    private ImageView main_lvl1_img1;
    private ImageView main_lvl1_img2;
    private ImageView main_lvl1_img3;
    private ImageView main_lvl1_img4;
    private TextView main_numero_nivel1;
    //Nivel 2
    private ConstraintLayout parent_img_nivel2;
    private ImageView main_lvl2_img1;
    private ImageView main_lvl2_img2;
    private ImageView main_lvl2_img3;
    private ImageView main_lvl2_img4;
    private TextView main_numero_nivel2;
    private Button btnInicio_nivel2;
    private int actual_lvl_nivel2 = 0;
    //Sonido
    private SoundPool sp;
    private int sound_abrir;
    private int sound_dialog;
    //Add_Monedas
    private RewardedAd mRewardedAd;
    private int valor_twitter = 0;
    private int valor_facebook = 0;
    private int valor_whatsapp = 0;
    private static final String nombre_app = "Dota 2: Heroes Quiz";
    private static final String link = "https://play.google.com/store/apps/details?id=com.orizevtechnologies.quiz_app";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Show_Hide_SystemUI();
        init_view();
        lottie();
        shared_preference_niveles();
        sonidos();
        click_monedas();
        imagenes_nivel1();
        imagenes_nivel2();
        init_value();
        //Add_Monedas
        dialog_shared_preference();
    }

    protected void onPause() {
        super.onPause();
        if (actual_lvl % 10 == 0) {
            new Handler().postDelayed(() -> {
                if (sp != null ) {
                    sp.release();
                }}, 500);
        }
    }

    protected void onResume() {
        super.onResume();
        if (sp == null) {
            sp = new SoundPool.Builder().setMaxStreams(10).build();
            sound_abrir = sp.load(this, R.raw.abrir,1);
            sound_dialog = sp.load(this, R.raw.show_dialog,1);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        shared_preference_niveles();
        btn_main_coins.setText(String.valueOf(gold));
    }

    @Override
    public void onBackPressed() {
        detalle_sonido(2);
        moveTaskToBack(true);
    }

    //Inicializar Variables
    private void init_view() {
        btn_main_config = findViewById(R.id.btn_main_config);
        btn_main_coins = findViewById(R.id.btn_main_coins);
        //Nivel 1
        parent_img_nivel1 = findViewById(R.id.parent_img_main1);
        main_lvl1_img1 = findViewById(R.id.main_lvl1_img1);
        main_lvl1_img2 = findViewById(R.id.main_lvl1_img2);
        main_lvl1_img3 = findViewById(R.id.main_lvl1_img3);
        main_lvl1_img4 = findViewById(R.id.main_lvl1_img4);
        main_numero_nivel1 = findViewById(R.id.main_numero_nivel1);
        btnInicio_nivel1 = findViewById(R.id.btnInicio_nivel1);
        //Nivel 2
        parent_img_nivel2 = findViewById(R.id.parent_img_main2);
        main_lvl2_img1 = findViewById(R.id.main_lvl2_img1);
        main_lvl2_img2 = findViewById(R.id.main_lvl2_img2);
        main_lvl2_img3 = findViewById(R.id.main_lvl2_img3);
        main_lvl2_img4 = findViewById(R.id.main_lvl2_img4);
        main_numero_nivel2 = findViewById(R.id.main_numero_nivel2);
        btnInicio_nivel2 = findViewById(R.id.btnInicio_nivel2);

    }

    //Valorizar Variables
    private void init_value() {
        //Colorcar Valor de oro
        btn_main_coins.setText(String.valueOf(gold));
        //Nivel 1
        parent_img_nivel1.setOnClickListener(view -> abrir_nivel1());
        btnInicio_nivel1.setOnClickListener(view -> abrir_nivel1());
        //Nivel 2
        parent_img_nivel2.setOnClickListener(view -> abrir_nivel2());
        btnInicio_nivel2.setOnClickListener(view -> abrir_nivel2());
    }

    //Mostrar Animacion Configuracion
    private void lottie() {
        btn_main_config.setOnClickListener(view -> {
            detalle_sonido(1);
            btn_main_config.playAnimation();
            Intent intent = new Intent(MainActivity.this, configuracion.class);
            startActivity(intent);
            overridePendingTransition( R.anim.slide_up, R.anim.stay_vertical);
        });
    }

    //OnClick Monedas
    private void click_monedas() {
        btn_main_coins.setOnClickListener(view -> {
            detalle_sonido(2);
            dialog_agregar_monedas();
        });
    }

    //Cargar Sonido
    private void sonidos() {
        sp = new SoundPool.Builder().setMaxStreams(10).build();
        sound_abrir = sp.load(this, R.raw.abrir,1);
        sound_dialog = sp.load(this, R.raw.show_dialog,1);
    }

    //Reproducir Sonido
    private void detalle_sonido(int tipo) {
        if (valor_volumen==1) {
            switch (tipo) {
                case 1:
                    sp.play(sound_abrir, 1,1,1,0,1);
                    break;
                case 2:
                    sp.play(sound_dialog, 1,1,1,0,1);
                    break;
            }
        }
    }

    //Ocultar UI
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

    //Shared Preference
    private void shared_preference_niveles() {
        //Nivel1
        SharedPreferences sp = this.getSharedPreferences("shared", Context.MODE_PRIVATE);
        gold = sp.getInt(getString(R.string.recompensa), 500);
        actual_lvl = sp.getInt(getString(R.string.nivel_actual), 1);
        valor_volumen = sp.getInt(getString(R.string.estado_volumen), 1);
        //Nivel2
        actual_lvl_nivel2 = sp.getInt(getString(R.string.nivel_actual_2), 1);
    }

    /* NIVEL A  (nivel= 60)*/
    private void abrir_nivel1() {
        detalle_sonido(1);
        Intent intent = new Intent(MainActivity.this, Nivel1.class);
        //Bundle extras = new Bundle();
        //extras.putInt("num_nivel",1);
        //intent.putExtras(extras);
        startActivity(intent);
        overridePendingTransition( R.anim.slide_right_in, R.anim.stay_vertical);
    }

    private void imagenes_nivel1() {
        main_numero_nivel1.setText(String.valueOf(actual_lvl));
        switch (actual_lvl) {
            case 1:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel1_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel1_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel1_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel1_d));
                break;
            case 2:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel2_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel2_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel2_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel2_d));
                break;
            case 3:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel3_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel3_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel3_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel3_d));
                break;
            case 4:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel4_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel4_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel4_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel4_d));
                break;
            case 5:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel5_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel5_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel5_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel5_d));
                break;
            case 6:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel6_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel6_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel6_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel6_d));
                break;
            case 7:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel7_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel7_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel7_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel7_d));
                break;
            case 8:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel8_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel8_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel8_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel8_d));
                break;
            case 9:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel9_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel9_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel9_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel9_d));
                break;
            case 10:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel10_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel10_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel10_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel10_d));
                break;
            case 11:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel11_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel11_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel11_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel11_d));
                break;
            case 12:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel12_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel12_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel12_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel12_d));
                break;
            case 13:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel13_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel13_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel13_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel13_d));
                break;
            case 14:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel14_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel14_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel14_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel14_d));
                break;
            case 15:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel15_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel15_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel15_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel15_d));
                break;
            case 16:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel16_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel16_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel16_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel16_d));
                break;
            case 17:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel17_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel17_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel17_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel17_d));
                break;
            case 18:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel18_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel18_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel18_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel18_d));
                break;
            case 19:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel19_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel19_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel19_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel19_d));
                break;
            case 20:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel20_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel20_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel20_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel20_d));
                break;
            case 21:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel21_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel21_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel21_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel21_d));
                break;
            case 22:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel22_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel22_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel22_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel22_d));
                break;
            case 23:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel23_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel23_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel23_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel23_d));
                break;
            case 24:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel24_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel24_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel24_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel24_d));
                break;
            case 25:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel25_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel25_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel25_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel25_d));
                break;
            case 26:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel26_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel26_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel26_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel26_d));
                break;
            case 27:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel27_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel27_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel27_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel27_d));
                break;
            case 28:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel28_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel28_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel28_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel28_d));
                break;
            case 29:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel29_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel29_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel29_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel29_d));
                break;
            case 30:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel30_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel30_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel30_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel30_d));
                break;
            case 31:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel31_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel31_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel31_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel31_d));
                break;
            case 32:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel32_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel32_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel32_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel32_d));
                break;
            case 33:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel33_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel33_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel33_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel33_d));
                break;
            case 34:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel34_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel34_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel34_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel34_d));
                break;
            case 35:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel35_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel35_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel35_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel35_d));
                break;
            case 36:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel36_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel36_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel36_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel36_d));
                break;
            case 37:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel37_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel37_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel37_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel37_d));
                break;
            case 38:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel38_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel38_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel38_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel38_d));
                break;
            case 39:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel39_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel39_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel39_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel39_d));
                break;
            case 40:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel40_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel40_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel40_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel40_d));
                break;
            case 41:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel41_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel41_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel41_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel41_d));
                break;
            case 42:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel42_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel42_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel42_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel42_d));
                break;
            case 43:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel43_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel43_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel43_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel43_d));
                break;
            case 44:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel44_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel44_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel44_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel44_d));
                break;
            case 45:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel45_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel45_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel45_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel45_d));
                break;
            case 46:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel46_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel46_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel46_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel46_d));
                break;
            case 47:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel47_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel47_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel47_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel47_d));
                break;
            case 48:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel48_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel48_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel48_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel48_d));
                break;
            case 49:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel49_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel49_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel49_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel49_d));
                break;
            case 50:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel50_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel50_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel50_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel50_d));
                break;
            case 51:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel51_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel51_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel51_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel51_d));
                break;
            case 52:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel52_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel52_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel52_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel52_d));
                break;
            case 53:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel53_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel53_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel53_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel53_d));
                break;
            case 54:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel54_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel54_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel54_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel54_d));
                break;
            case 55:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel55_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel55_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel55_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel55_d));
                break;
            case 56:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel56_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel56_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel56_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel56_d));
                break;
            case 57:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel57_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel57_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel57_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel57_d));
                break;
            case 58:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel58_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel58_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel58_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel58_d));
                break;
            case 59:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel59_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel59_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel59_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel59_d));
                break;
            case 60:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel60_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel60_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel60_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel60_d));
                break;

            default:
                main_lvl1_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel1_a));
                main_lvl1_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel1_b));
                main_lvl1_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel1_c));
                main_lvl1_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nivel1_d));
                SharedPreferences sp = this.getSharedPreferences("shared", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                int lvl_actualizado = 1;
                editor.putInt(getString(R.string.nivel_actual), lvl_actualizado);
                editor.apply();
                break;
        }
    }

    /* NIVEL B */
    private void abrir_nivel2() {
        detalle_sonido(1);
        Intent intent = new Intent(MainActivity.this, Nivel2.class);
        //Bundle extras = new Bundle();
        //extras.putInt("num_nivel",2);
        //intent.putExtras(extras);
        startActivity(intent);
        overridePendingTransition( R.anim.slide_right_in, R.anim.stay_vertical);
    }

    private void imagenes_nivel2() {
        main_numero_nivel2.setText(String.valueOf(actual_lvl_nivel2));
        switch (actual_lvl_nivel2) {
            case 1:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel1_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel1_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel1_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel1_d));
                break;
            case 2:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel2_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel2_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel2_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel2_d));
                break;
            case 3:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel3_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel3_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel3_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel3_d));
                break;
            case 4:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel4_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel4_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel4_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel4_d));
                break;
            case 5:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel5_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel5_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel5_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel5_d));
                break;
            case 6:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel6_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel6_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel6_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel6_d));
                break;
            case 7:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel7_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel7_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel7_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel7_d));
                break;
            case 8:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel8_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel8_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel8_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel8_d));
                break;
            case 9:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel9_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel9_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel9_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel9_d));
                break;
            case 10:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel10_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel10_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel10_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel10_d));
                break;
            case 11:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel11_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel11_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel11_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel11_d));
                break;
            case 12:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel12_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel12_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel12_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel12_d));
                break;
            case 13:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel13_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel13_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel13_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel13_d));
                break;
            case 14:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel14_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel14_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel14_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel14_d));
                break;
            case 15:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel15_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel15_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel15_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel15_d));
                break;
            case 16:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel16_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel16_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel16_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel16_d));
                break;
            case 17:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel17_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel17_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel17_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel17_d));
                break;
            case 18:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel18_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel18_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel18_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel18_d));
                break;
            case 19:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel19_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel19_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel19_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel19_d));
                break;
            case 20:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel20_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel20_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel20_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel20_d));
                break;
            case 21:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel21_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel21_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel21_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel21_d));
                break;
            case 22:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel22_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel22_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel22_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel22_d));
                break;
            case 23:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel23_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel23_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel23_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel23_d));
                break;
            case 24:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel24_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel24_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel24_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel24_d));
                break;
            case 25:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel25_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel25_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel25_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel25_d));
                break;
            case 26:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel26_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel26_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel26_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel26_d));
                break;
            case 27:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel27_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel27_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel27_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel27_d));
                break;
            case 28:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel28_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel28_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel28_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel28_d));
                break;
            case 29:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel29_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel29_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel29_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel29_d));
                break;
            case 30:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel30_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel30_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel30_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel30_d));
                break;
                /*
            case 31:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel31_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel31_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel31_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel31_d));
                break;
            case 32:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel32_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel32_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel32_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel32_d));
                break;
            case 33:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel33_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel33_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel33_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel33_d));
                break;
            case 34:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel34_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel34_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel34_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel34_d));
                break;
            case 35:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel35_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel35_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel35_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel35_d));
                break;
            case 36:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel36_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel36_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel36_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel36_d));
                break;
            case 37:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel37_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel37_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel37_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel37_d));
                break;
            case 38:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel38_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel38_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel38_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel38_d));
                break;
            case 39:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel39_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel39_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel39_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel39_d));
                break;
            case 40:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel40_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel40_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel40_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel40_d));
                break;
            case 41:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel41_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel41_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel41_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel41_d));
                break;
            case 42:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel42_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel42_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel42_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel42_d));
                break;
            case 43:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel43_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel43_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel43_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel43_d));
                break;
            case 44:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel44_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel44_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel44_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel44_d));
                break;
            case 45:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel45_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel45_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel45_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel45_d));
                break;
            case 46:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel46_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel46_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel46_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel46_d));
                break;
            case 47:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel47_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel47_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel47_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel47_d));
                break;
            case 48:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel48_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel48_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel48_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel48_d));
                break;
            case 49:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel49_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel49_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel49_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel49_d));
                break;
            case 50:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel50_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel50_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel50_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel50_d));
                break;
            case 51:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel51_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel51_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel51_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel51_d));
                break;
            case 52:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel52_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel52_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel52_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel52_d));
                break;
            case 53:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel53_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel53_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel53_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel53_d));
                break;
            case 54:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel54_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel54_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel54_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel54_d));
                break;
            case 55:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel55_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel55_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel55_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel55_d));
                break;
            case 56:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel56_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel56_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel56_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel56_d));
                break;
            case 57:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel57_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel57_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel57_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel57_d));
                break;
            case 58:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel58_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel58_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel58_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel58_d));
                break;
            case 59:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel59_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel59_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel59_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel59_d));
                break;
            case 60:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel60_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel60_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel60_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel60_d));
                break;
            */
            default:
                main_lvl2_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel1_a));
                main_lvl2_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel1_b));
                main_lvl2_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel1_c));
                main_lvl2_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel1_d));
                SharedPreferences sp = this.getSharedPreferences("shared", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                int lvl_actualizado = 1;
                editor.putInt(getString(R.string.nivel_actual), lvl_actualizado);
                editor.apply();
                break;
        }
    }




    /* DIALOG AGREGAR MONEDAS */
    private void dialog_agregar_monedas() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_monedas);
        //Inicializar Variables
        FloatingActionButton btnSalir = dialog.findViewById(R.id.btnSalir);
        ConstraintLayout root = dialog.findViewById(R.id.root);
        Button btnText = dialog.findViewById(R.id.txt_monedas);
        ConstraintLayout share_twitter = dialog.findViewById(R.id.share_twitter);
        ConstraintLayout share_facebook = dialog.findViewById(R.id.share_facebook);
        ConstraintLayout share_whatsapp = dialog.findViewById(R.id.share_whatsapp);
        ConstraintLayout reward_video = dialog.findViewById(R.id.reward_video);
        reward_video.setEnabled(false);
        load_ads(reward_video);
        dialog_boolean_enabled(reward_video, share_twitter, share_whatsapp, share_facebook);
        //OnClick_Datos
        btnText.setText(String.valueOf(gold));
        dialog.setOnCancelListener(dialogInterface -> { detalle_sonido(2); dialog.dismiss();});
        root.setOnClickListener(view -> {detalle_sonido(2); dialog.dismiss();});
        btnText.setOnClickListener( view -> {});
        btnSalir.setOnClickListener(view -> { detalle_sonido(2); dialog.dismiss(); });
        //OnClic_Métodos
        share_twitter.setOnClickListener( view -> {detalle_sonido(2); dialog_share("twitter", btnText, share_twitter); });
        share_facebook.setOnClickListener( view -> {detalle_sonido(2); dialog_share("facebook", btnText, share_facebook);});
        share_whatsapp.setOnClickListener( view -> {detalle_sonido(2); dialog_share("whatsapp", btnText, share_whatsapp);});
        reward_video.setOnClickListener( view -> { detalle_sonido(2); show_ads(reward_video, btnText); });

        dialog_detalle(dialog);
    }

    private void dialog_boolean_enabled(ConstraintLayout layout_video, ConstraintLayout layout_twitter,
                                        ConstraintLayout layout_whatsapp, ConstraintLayout layout_facebook) {
        if (layout_video.isEnabled()) {
            layout_video.setBackgroundResource(R.drawable.background_add_monedas);
        } else {
            layout_video.setBackgroundResource(R.drawable.background_add_monedas_disable);
        }
        if (valor_twitter == 1) {
            layout_twitter.setEnabled(false);
            layout_twitter.setBackgroundResource(R.drawable.background_add_monedas_disable);
        }
        if (valor_whatsapp == 1) {
            layout_whatsapp.setEnabled(false);
            layout_whatsapp.setBackgroundResource(R.drawable.background_add_monedas_disable);
        }
        if (valor_facebook == 1) {
            layout_facebook.setEnabled(false);
            layout_facebook.setBackgroundResource(R.drawable.background_add_monedas_disable);
        }
    }

    //Codigo Prueba: ca-app-pub-3940256099942544/5224354917
    //Codigo Produccion: ca-app-pub-8291876581767416/8793622809
    private void load_ads(ConstraintLayout layout_video) {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(this, "ca-app-pub-8291876581767416/8793622809",
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        layout_video.setEnabled(false);
                        layout_video.setBackgroundResource(R.drawable.background_add_monedas_disable);
                        mRewardedAd = null;
                    }
                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        mRewardedAd = rewardedAd;
                        layout_video.setEnabled(true);
                        layout_video.setBackgroundResource(R.drawable.selector_background_add_monedas);
                    }
                });
    }

    private void show_ads(ConstraintLayout layout_video, Button btnText) {
        if (mRewardedAd != null) {
            mRewardedAd.show(this, rewardItem -> {
                // Handle the reward.
                dialog_add_conis(50);
                int txtlvl = Integer.parseInt((String) btn_main_coins.getText());
                txtlvl = txtlvl+50;
                btn_main_coins.setText(String.valueOf(txtlvl));
                btnText.setText(String.valueOf(txtlvl));
                Toast.makeText(MainActivity.this, "¡50 monedas conseguidas!", Toast.LENGTH_SHORT).show();
                layout_video.setBackgroundResource(R.drawable.background_add_monedas_disable);
                layout_video.setEnabled(false);
            });
        }
    }

    private void dialog_share(String media, Button btnText, ConstraintLayout layout_social) {
        SharedPreferences sp = this.getSharedPreferences("redes", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        String mensaje = "Mira este nuevo juego \"" + nombre_app + "\" en Google Play! juégalo GRATIS Ahora - " + link;
        switch (media) {
            case "twitter":
                String tweetUrl = "https://twitter.com/intent/tweet?text="+ mensaje;
                Uri uri = Uri.parse(tweetUrl);
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
                dialog_add_conis(20);
                editor.putInt(getString(R.string.twitter),1);
                editor.apply();
                layout_social.setEnabled(false);
                layout_social.setBackgroundResource(R.drawable.background_add_monedas_disable);
                break;
            case "facebook":
                String urlToShare = link;
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                // intent.putExtra(Intent.EXTRA_SUBJECT, "Foo bar"); // NB: has no effect!
                intent.putExtra(Intent.EXTRA_TEXT, urlToShare);
                // See if official Facebook app is found
                boolean facebookAppFound = false;
                List<ResolveInfo> matches = getPackageManager().queryIntentActivities(intent, 0);
                for (ResolveInfo info : matches) {
                    if (info.activityInfo.packageName.toLowerCase().startsWith("com.facebook.katana")) {
                        intent.setPackage(info.activityInfo.packageName);
                        facebookAppFound = true;
                        break;
                    }
                }
                // As fallback, launch sharer.php in a browser
                if (!facebookAppFound) {
                    String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + urlToShare;
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
                }
                startActivity(intent);
                dialog_add_conis(20);
                editor.putInt(getString(R.string.facebook),1);
                editor.apply();
                layout_social.setEnabled(false);
                layout_social.setBackgroundResource(R.drawable.background_add_monedas_disable);
                break;
            case "whatsapp":
                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.setPackage("com.whatsapp");
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, mensaje);
                try {
                    startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(this, "Whatsapp have not been installed.", Toast.LENGTH_SHORT).show();
                }
                dialog_add_conis(20);
                editor.putInt(getString(R.string.whatsapp),1);
                editor.apply();
                layout_social.setEnabled(false);
                layout_social.setBackgroundResource(R.drawable.background_add_monedas_disable);
                break;
        }
        int txtlvl = Integer.parseInt((String) btn_main_coins.getText());
        txtlvl = txtlvl+20;
        btn_main_coins.setText(String.valueOf(txtlvl));
        btnText.setText(String.valueOf(txtlvl));
    }

    private void dialog_detalle(Dialog dialog) {
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        if (dialog.getWindow() != null){
            dialog.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation_coins;
        }
        dialog.show();
        dialog.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
    }

    private  void dialog_add_conis(int recompensa) {
        int recom_actualizado = gold + recompensa;
        SharedPreferences sp = this.getSharedPreferences("shared", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(getString(R.string.recompensa),recom_actualizado);
        editor.apply();
    }

    private void dialog_shared_preference() {
        SharedPreferences sp = this.getSharedPreferences("redes", Context.MODE_PRIVATE);
        valor_twitter = sp.getInt(getString(R.string.twitter), 0);
        valor_facebook = sp.getInt(getString(R.string.facebook), 0);
        valor_whatsapp = sp.getInt(getString(R.string.whatsapp), 0);
    }
}