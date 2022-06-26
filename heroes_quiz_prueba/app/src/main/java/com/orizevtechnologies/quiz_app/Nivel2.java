package com.orizevtechnologies.quiz_app;

import android.annotation.SuppressLint;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.transition.Fade;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class Nivel2 extends AppCompatActivity {

    //Lottie
    LottieAnimationView retroceso;
    //SharedPreference
    private int gold = 0;
    private int actual_lvl = 0;
    private int max_lvl = 0;
    private int valor_volumen = 0;
    private int herramienta_eliminar = 0;
    private int contador_agregar = 1;
    private int contador_eliminar = 1;
    //Imágenes
    private ImageView main_img1;
    private ImageView main_img2;
    private ImageView main_img3;
    private ImageView main_img4;
    //Completado_Layout
    private ConstraintLayout parent_completado;
    private ConstraintLayout completado_linea2;
    private LinearLayout comp_sup_1;
    private LinearLayout comp_sup_2;
    private LinearLayout comp_sup_3;
    private LinearLayout comp_sup_4;
    private LinearLayout comp_sup_5;
    private LinearLayout comp_sup_6;
    private LinearLayout comp_sup_7;
    private LinearLayout comp_sup_8;
    private LinearLayout comp_inf_1;
    private LinearLayout comp_inf_2;
    private LinearLayout comp_inf_3;
    private LinearLayout comp_inf_4;
    private LinearLayout comp_inf_5;
    private LinearLayout comp_inf_6;
    private LinearLayout comp_inf_7;
    //Completado_Texto
    private TextView completado_txt1;
    private TextView completado_txt2;
    private TextView completado_txt3;
    private TextView completado_txt4;
    private TextView completado_txt5;
    private TextView completado_txt6;
    private TextView completado_txt7;
    private TextView completado_txt8;
    private TextView completado_txt9;
    private TextView completado_txt10;
    private TextView completado_txt11;
    private TextView completado_txt12;
    private TextView completado_txt13;
    private TextView completado_txt14;
    private TextView completado_txt15;
    //Opciones_Layout
    private LinearLayout sup_1;
    private LinearLayout sup_2;
    private LinearLayout sup_3;
    private LinearLayout sup_4;
    private LinearLayout sup_5;
    private LinearLayout sup_6;
    private LinearLayout sup_7;
    private LinearLayout sup_8;
    private LinearLayout inf_1;
    private LinearLayout inf_2;
    private LinearLayout inf_3;
    private LinearLayout inf_4;
    private LinearLayout inf_5;
    private LinearLayout inf_6;
    private LinearLayout inf_7;
    private LinearLayout inf_8;
    //Opciones_Parent
    private LinearLayout opcion_parent1;
    private LinearLayout opcion_parent2;
    private LinearLayout opcion_parent3;
    private LinearLayout opcion_parent4;
    private LinearLayout opcion_parent5;
    private LinearLayout opcion_parent6;
    private LinearLayout opcion_parent7;
    private LinearLayout opcion_parent8;
    private LinearLayout opcion_parent9;
    private LinearLayout opcion_parent10;
    private LinearLayout opcion_parent11;
    private LinearLayout opcion_parent12;
    private LinearLayout opcion_parent13;
    private LinearLayout opcion_parent14;
    private LinearLayout opcion_parent15;
    private LinearLayout opcion_parent16;
    //Opciones_Texto
    private TextView opcion_txt1;
    private TextView opcion_txt2;
    private TextView opcion_txt3;
    private TextView opcion_txt4;
    private TextView opcion_txt5;
    private TextView opcion_txt6;
    private TextView opcion_txt7;
    private TextView opcion_txt8;
    private TextView opcion_txt9;
    private TextView opcion_txt10;
    private TextView opcion_txt11;
    private TextView opcion_txt12;
    private TextView opcion_txt13;
    private TextView opcion_txt14;
    private TextView opcion_txt15;
    private TextView opcion_txt16;
    //Gold
    private Button btnMonedas;
    //Nivel1
    private TextView elegir_niveles;
    //Herramientas
    private Button btnAgregar;
    private Button btnEliminar;
    private Button btnPasar;
    //CambioPalabras
    private String palabra_principal= "";
    private String numero_letras= "";
    //Sonido
    private SoundPool sp = null;
    private int sound_abrir = 0;
    private int sound_btn1 = 0;
    private int sound_btn2 = 0;
    private int sound_incorrecto = 0;
    private int sound_dialog = 0;
    private int sound_herramientas = 0;
    private int sound_ganar = 0;
    //Publicidad Interstitial
    private InterstitialAd mInterstitialAd;
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
        setContentView(R.layout.activity_nivel2);
        Show_Hide_SystemUI();
        init_view();
        sharedPreference();
        dialog_shared_preferences();
        shared_preference_herramientas();
        sonidos();
        palabra_a_buscar();
        cambiar_letras_opciones();
        click_opciones();
        click_completado();
        lottie();
        cambio_imagen_nivel();
        show_img_dialog_box();
        cambio_txt();
        init_values();
        click_herramientas();
        herramienta_eliminar_presionado();
        herramienta_agregar_letra();
        add_inter();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        sharedPreference();
        dialog_shared_preferences();
        btnMonedas.setText(String.valueOf(gold));
    }

    protected void onResume() {
        super.onResume();
        if (sp == null) {
            sp = new SoundPool.Builder().setMaxStreams(10).build();
            sound_abrir = sp.load(this, R.raw.abrir,1);
            sound_btn1 = sp.load(this, R.raw.boton1,1);
            sound_btn2 = sp.load(this, R.raw.boton2,1);
            sound_incorrecto = sp.load(this, R.raw.incorrecto,1);
            sound_dialog = sp.load(this, R.raw.show_dialog,1);
            sound_herramientas = sp.load(this, R.raw.herramientas,1);
            sound_ganar = sp.load(this, R.raw.ganar,1);
        }
    }

    @Override
    public void onBackPressed() {
        detalle_sonido("abrir");
        Intent intent = new Intent(Nivel2.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
    }

    protected void onPause() {
        super.onPause();
        new Handler().postDelayed(() -> {
            if (sp != null ) {
                sp.release();
                sp = null;
            }}, 500);
    }


    //Inicializacion de variables
    private void init_view() {
        parent_completado = findViewById(R.id.parent_completado_niveles);
        completado_linea2 = findViewById(R.id.completado_linea2);
        comp_sup_1 = findViewById(R.id.comp_sup_1);
        comp_sup_2 = findViewById(R.id.comp_sup_2);
        comp_sup_3 = findViewById(R.id.comp_sup_3);
        comp_sup_4 = findViewById(R.id.comp_sup_4);
        comp_sup_5 = findViewById(R.id.comp_sup_5);
        comp_sup_6 = findViewById(R.id.comp_sup_6);
        comp_sup_7 = findViewById(R.id.comp_sup_7);
        comp_sup_8 = findViewById(R.id.comp_sup_8);
        comp_inf_1 = findViewById(R.id.comp_inf_1);
        comp_inf_2 = findViewById(R.id.comp_inf_2);
        comp_inf_3 = findViewById(R.id.comp_inf_3);
        comp_inf_4 = findViewById(R.id.comp_inf_4);
        comp_inf_5 = findViewById(R.id.comp_inf_5);
        comp_inf_6 = findViewById(R.id.comp_inf_6);
        comp_inf_7 = findViewById(R.id.comp_inf_7);
        //Completado_Texto
        completado_txt1 = findViewById(R.id.comp_text_1);
        completado_txt2 = findViewById(R.id.comp_text_2);
        completado_txt3 = findViewById(R.id.comp_text_3);
        completado_txt4 = findViewById(R.id.comp_text_4);
        completado_txt5 = findViewById(R.id.comp_text_5);
        completado_txt6 = findViewById(R.id.comp_text_6);
        completado_txt7 = findViewById(R.id.comp_text_7);
        completado_txt8 = findViewById(R.id.comp_text_8);
        completado_txt9 = findViewById(R.id.comp_text_9);
        completado_txt10 = findViewById(R.id.comp_text_10);
        completado_txt11 = findViewById(R.id.comp_text_11);
        completado_txt12 = findViewById(R.id.comp_text_12);
        completado_txt13 = findViewById(R.id.comp_text_13);
        completado_txt14 = findViewById(R.id.comp_text_14);
        completado_txt15 = findViewById(R.id.comp_text_15);
        //Parent Opciones
        opcion_parent1 = findViewById(R.id.sup_parent_1);
        opcion_parent2 = findViewById(R.id.sup_parent_2);
        opcion_parent3 = findViewById(R.id.sup_parent_3);
        opcion_parent4 = findViewById(R.id.sup_parent_4);
        opcion_parent5 = findViewById(R.id.sup_parent_5);
        opcion_parent6 = findViewById(R.id.sup_parent_6);
        opcion_parent7 = findViewById(R.id.sup_parent_7);
        opcion_parent8 = findViewById(R.id.sup_parent_8);
        opcion_parent9 = findViewById(R.id.inf_parent_1);
        opcion_parent10 = findViewById(R.id.inf_parent_2);
        opcion_parent11 = findViewById(R.id.inf_parent_3);
        opcion_parent12 = findViewById(R.id.inf_parent_4);
        opcion_parent13 = findViewById(R.id.inf_parent_5);
        opcion_parent14 = findViewById(R.id.inf_parent_6);
        opcion_parent15 = findViewById(R.id.inf_parent_7);
        opcion_parent16 = findViewById(R.id.inf_parent_8);
        //Layout Opciones
        sup_1 = findViewById(R.id.sup_1);
        sup_2 = findViewById(R.id.sup_2);
        sup_3 = findViewById(R.id.sup_3);
        sup_4 = findViewById(R.id.sup_4);
        sup_5 = findViewById(R.id.sup_5);
        sup_6 = findViewById(R.id.sup_6);
        sup_7 = findViewById(R.id.sup_7);
        sup_8 = findViewById(R.id.sup_8);
        inf_1 = findViewById(R.id.inf_1);
        inf_2 = findViewById(R.id.inf_2);
        inf_3 = findViewById(R.id.inf_3);
        inf_4 = findViewById(R.id.inf_4);
        inf_5 = findViewById(R.id.inf_5);
        inf_6 = findViewById(R.id.inf_6);
        inf_7 = findViewById(R.id.inf_7);
        inf_8 = findViewById(R.id.inf_8);
        //Texto Opciones
        opcion_txt1 = findViewById(R.id.op_text_1);
        opcion_txt2 = findViewById(R.id.op_text_2);
        opcion_txt3 = findViewById(R.id.op_text_3);
        opcion_txt4 = findViewById(R.id.op_text_4);
        opcion_txt5 = findViewById(R.id.op_text_5);
        opcion_txt6 = findViewById(R.id.op_text_6);
        opcion_txt7 = findViewById(R.id.op_text_7);
        opcion_txt8 = findViewById(R.id.op_text_8);
        opcion_txt9 = findViewById(R.id.op_text_9);
        opcion_txt10 = findViewById(R.id.op_text_10);
        opcion_txt11 = findViewById(R.id.op_text_11);
        opcion_txt12 = findViewById(R.id.op_text_12);
        opcion_txt13 = findViewById(R.id.op_text_13);
        opcion_txt14 = findViewById(R.id.op_text_14);
        opcion_txt15 = findViewById(R.id.op_text_15);
        opcion_txt16 = findViewById(R.id.op_text_16);
        //Lottie
        retroceso = findViewById(R.id.retroceso);
        //Gold
        btnMonedas = findViewById(R.id.btnMonedas);
        //Nivel
        elegir_niveles = findViewById(R.id.elegir_niveles);
        //hermiaentas
        btnAgregar = findViewById(R.id.btnAgregar);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnPasar = findViewById(R.id.btnPasar);
        //Imágenes
        main_img1 = findViewById(R.id.nivel1_sup1);
        main_img2 = findViewById(R.id.nivel1_sup2);
        main_img3 = findViewById(R.id.nivel1_inf1);
        main_img4 = findViewById(R.id.nivel1_inf2);
    }

    @SuppressLint("SetTextI18n")
    private void init_values() {
        btnMonedas.setText(String.valueOf(gold));
        btnMonedas.setOnClickListener(view -> click_monedas());
        elegir_niveles.setText(""+actual_lvl);
        main_img1.setClipToOutline(true);
        main_img2.setClipToOutline(true);
        main_img3.setClipToOutline(true);
        main_img4.setClipToOutline(true);
    }

    //ClickLottie
    private void lottie() {
        retroceso.setOnClickListener(view -> {
            detalle_sonido("abrir");
            retroceso.playAnimation();
            Intent intent = new Intent(Nivel2.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
        });
    }

    //Aplicar sonido a cada imagen
    private void show_img_dialog_box() {
        main_img1.setOnClickListener(view -> detalle_sonido("dialog"));
        main_img2.setOnClickListener(view -> detalle_sonido("dialog"));
        main_img3.setOnClickListener(view -> detalle_sonido("dialog"));
        main_img4.setOnClickListener(view -> detalle_sonido("dialog"));
    }

    //Función para ocultar las letras "opciones" al ser clickeadas
    private void click_opciones() {
        sup_1.setOnClickListener(view -> detalle_click_opciones(sup_1, opcion_parent1, opcion_txt1));
        sup_2.setOnClickListener(view -> detalle_click_opciones(sup_2, opcion_parent2, opcion_txt2));
        sup_3.setOnClickListener(view -> detalle_click_opciones(sup_3, opcion_parent3, opcion_txt3));
        sup_4.setOnClickListener(view -> detalle_click_opciones(sup_4, opcion_parent4, opcion_txt4));
        sup_5.setOnClickListener(view -> detalle_click_opciones(sup_5, opcion_parent5, opcion_txt5));
        sup_6.setOnClickListener(view -> detalle_click_opciones(sup_6, opcion_parent6, opcion_txt6));
        sup_7.setOnClickListener(view -> detalle_click_opciones(sup_7, opcion_parent7, opcion_txt7));
        sup_8.setOnClickListener(view -> detalle_click_opciones(sup_8, opcion_parent8, opcion_txt8));
        inf_1.setOnClickListener(view -> detalle_click_opciones(inf_1, opcion_parent9, opcion_txt9));
        inf_2.setOnClickListener(view -> detalle_click_opciones(inf_2, opcion_parent10, opcion_txt10));
        inf_3.setOnClickListener(view -> detalle_click_opciones(inf_3, opcion_parent11, opcion_txt11));
        inf_4.setOnClickListener(view -> detalle_click_opciones(inf_4, opcion_parent12, opcion_txt12));
        inf_5.setOnClickListener(view -> detalle_click_opciones(inf_5, opcion_parent13, opcion_txt13));
        inf_6.setOnClickListener(view -> detalle_click_opciones(inf_6, opcion_parent14, opcion_txt14));
        inf_7.setOnClickListener(view -> detalle_click_opciones(inf_7, opcion_parent15, opcion_txt15));
        inf_8.setOnClickListener(view -> detalle_click_opciones(inf_8, opcion_parent16, opcion_txt16));
    }

    //Mostrar la letra en "completados" luego de clickear las teclas "opciones"
    private void mostrar_completado(String letra) {
        if (completado_txt1.getVisibility() == View.INVISIBLE) {
            detalle_mostrar_completo(letra, comp_sup_1, completado_txt1);
        } else if (completado_txt2.getVisibility() == View.INVISIBLE) {
            detalle_mostrar_completo(letra, comp_sup_2, completado_txt2);
        } else if (completado_txt3.getVisibility() == View.INVISIBLE) {
            detalle_mostrar_completo(letra, comp_sup_3, completado_txt3);
        } else if (completado_txt4.getVisibility() == View.INVISIBLE) {
            detalle_mostrar_completo(letra, comp_sup_4, completado_txt4);
        } else if (completado_txt5.getVisibility() == View.INVISIBLE) {
            detalle_mostrar_completo(letra, comp_sup_5, completado_txt5);
        } else if (completado_txt6.getVisibility() == View.INVISIBLE) {
            detalle_mostrar_completo(letra, comp_sup_6, completado_txt6);
        } else if (completado_txt7.getVisibility() == View.INVISIBLE) {
            detalle_mostrar_completo(letra, comp_sup_7, completado_txt7);
        } else if (completado_txt8.getVisibility() == View.INVISIBLE) {
            detalle_mostrar_completo(letra, comp_sup_8, completado_txt8);
        } else if (completado_txt9.getVisibility() == View.INVISIBLE) {
            detalle_mostrar_completo(letra, comp_inf_1, completado_txt9);
        } else if (completado_txt10.getVisibility() == View.INVISIBLE) {
            detalle_mostrar_completo(letra, comp_inf_2, completado_txt10);
        } else if (completado_txt11.getVisibility() == View.INVISIBLE) {
            detalle_mostrar_completo(letra, comp_inf_3, completado_txt11);
        } else if (completado_txt12.getVisibility() == View.INVISIBLE) {
            detalle_mostrar_completo(letra, comp_inf_4, completado_txt12);
        } else if (completado_txt13.getVisibility() == View.INVISIBLE) {
            detalle_mostrar_completo(letra, comp_inf_5, completado_txt13);
        } else if (completado_txt14.getVisibility() == View.INVISIBLE) {
            detalle_mostrar_completo(letra, comp_inf_6, completado_txt14);
        } else if (completado_txt15.getVisibility() == View.INVISIBLE) {
            detalle_mostrar_completo(letra, comp_inf_7, completado_txt15);
        }
    }

    //Funcion para ocultar las letras "completados" al ser clickeados
    private void click_completado() {
        completado_txt1.setOnClickListener(view -> detalle_click_completo(comp_sup_1, completado_txt1));
        completado_txt2.setOnClickListener(view -> detalle_click_completo(comp_sup_2, completado_txt2));
        completado_txt3.setOnClickListener(view -> detalle_click_completo(comp_sup_3, completado_txt3));
        completado_txt4.setOnClickListener(view -> detalle_click_completo(comp_sup_4, completado_txt4));
        completado_txt5.setOnClickListener(view -> detalle_click_completo(comp_sup_5, completado_txt5));
        completado_txt6.setOnClickListener(view -> detalle_click_completo(comp_sup_6, completado_txt6));
        completado_txt7.setOnClickListener(view -> detalle_click_completo(comp_sup_7, completado_txt7));
        completado_txt8.setOnClickListener(view -> detalle_click_completo(comp_sup_8, completado_txt8));
        completado_txt9.setOnClickListener(view -> detalle_click_completo(comp_inf_1, completado_txt9));
        completado_txt10.setOnClickListener(view -> detalle_click_completo(comp_inf_2, completado_txt10));
        completado_txt11.setOnClickListener(view -> detalle_click_completo(comp_inf_3, completado_txt11));
        completado_txt12.setOnClickListener(view -> detalle_click_completo(comp_inf_4, completado_txt12));
        completado_txt13.setOnClickListener(view -> detalle_click_completo(comp_inf_5, completado_txt13));
        completado_txt14.setOnClickListener(view -> detalle_click_completo(comp_inf_6, completado_txt14));
        completado_txt15.setOnClickListener(view -> detalle_click_completo(comp_inf_7, completado_txt15));
    }

    //Mostrar la letra en "opciones" nuevamente luego de presionar "completados"
    private void mostrar_opciones(String letra) {
        String texto1 = (String) opcion_txt1.getText();
        String texto2 = (String) opcion_txt2.getText();
        String texto3 = (String) opcion_txt3.getText();
        String texto4 = (String) opcion_txt4.getText();
        String texto5 = (String) opcion_txt5.getText();
        String texto6 = (String) opcion_txt6.getText();
        String texto7 = (String) opcion_txt7.getText();
        String texto8 = (String) opcion_txt8.getText();
        String texto9 = (String) opcion_txt9.getText();
        String texto10 = (String) opcion_txt10.getText();
        String texto11 = (String) opcion_txt11.getText();
        String texto12 = (String) opcion_txt12.getText();
        String texto13 = (String) opcion_txt13.getText();
        String texto14 = (String) opcion_txt14.getText();
        String texto15 = (String) opcion_txt15.getText();
        String texto16 = (String) opcion_txt16.getText();
        if (texto1.equals(letra) && sup_1.getVisibility() == View.GONE) {
            detalle_mostrar_opcion(sup_1, opcion_parent1);
        } else if (texto2.equals(letra) && sup_2.getVisibility() == View.GONE) {
            detalle_mostrar_opcion(sup_2, opcion_parent2);
        } else if (texto3.equals(letra) && sup_3.getVisibility() == View.GONE) {
            detalle_mostrar_opcion(sup_3, opcion_parent3);
        } else if (texto4.equals(letra) && sup_4.getVisibility() == View.GONE) {
            detalle_mostrar_opcion(sup_4, opcion_parent4);
        } else if (texto5.equals(letra) && sup_5.getVisibility() == View.GONE) {
            detalle_mostrar_opcion(sup_5, opcion_parent5);
        } else if (texto6.equals(letra) && sup_6.getVisibility() == View.GONE) {
            detalle_mostrar_opcion(sup_6, opcion_parent6);
        } else if (texto7.equals(letra) && sup_7.getVisibility() == View.GONE) {
            detalle_mostrar_opcion(sup_7, opcion_parent7);
        } else if (texto8.equals(letra) && sup_8.getVisibility() == View.GONE) {
            detalle_mostrar_opcion(sup_8, opcion_parent8);
        } else if (texto9.equals(letra) && inf_1.getVisibility() == View.GONE) {
            detalle_mostrar_opcion(inf_1, opcion_parent9);
        } else if (texto10.equals(letra) && inf_2.getVisibility() == View.GONE) {
            detalle_mostrar_opcion(inf_2, opcion_parent10);
        } else if (texto11.equals(letra) && inf_3.getVisibility() == View.GONE) {
            detalle_mostrar_opcion(inf_3, opcion_parent11);
        } else if (texto12.equals(letra) && inf_4.getVisibility() == View.GONE) {
            detalle_mostrar_opcion(inf_4, opcion_parent12);
        } else if (texto13.equals(letra) && inf_5.getVisibility() == View.GONE) {
            detalle_mostrar_opcion(inf_5, opcion_parent13);
        } else if (texto14.equals(letra) && inf_6.getVisibility() == View.GONE) {
            detalle_mostrar_opcion(inf_6, opcion_parent14);
        } else if (texto15.equals(letra) && inf_7.getVisibility() == View.GONE) {
            detalle_mostrar_opcion(inf_7, opcion_parent15);
        } else if (texto16.equals(letra) && inf_8.getVisibility() == View.GONE) {
            detalle_mostrar_opcion(inf_8, opcion_parent16);
        }
    }

    private void det_detalle_click_opciones(LinearLayout target, LinearLayout parent, TextView op_text) {
        Transition transition = new Fade();
        transition.setDuration(100);
        transition.addTarget(target);
        TransitionManager.beginDelayedTransition(parent, transition);
        target.setVisibility(View.GONE);
        String texto = (String) op_text.getText();
        mostrar_completado(texto);
        texto_completo();
        target.setEnabled(false);
        new Handler().postDelayed(() -> target.setEnabled(true), 100);
    }

    private void detalle_mostrar_completo(String letra, LinearLayout target, TextView text) {
        text.setText(letra);
        Transition transition = new Fade();
        transition.setDuration(100);
        transition.addTarget(text);
        TransitionManager.beginDelayedTransition(target, transition);
        text.setVisibility(View.VISIBLE);
    }

    private void detalle_click_completo(LinearLayout parent, TextView comp_text) {
        detalle_sonido("boton2");
        Transition transition = new Fade();
        transition.setDuration(100);
        transition.addTarget(comp_text);
        TransitionManager.beginDelayedTransition(parent, transition);
        comp_text.setVisibility(View.INVISIBLE);
        String texto = (String) comp_text.getText();
        mostrar_opciones(texto);
        cambiar_color("blanco");
    }

    private void detalle_mostrar_opcion(LinearLayout target, LinearLayout parent) {
        Transition transition = new Fade();
        transition.setDuration(100);
        transition.addTarget(target);
        TransitionManager.beginDelayedTransition(parent, transition);
        target.setVisibility(View.VISIBLE);
    }

    //Cambiar color a las letras de "completados"
    private void cambiar_color(String color) {
        switch (color) {
            case "blanco":
                detalle_cambiar_color(completado_txt1, R.color.texto_agregado, R.color.texto_completado);
                detalle_cambiar_color(completado_txt2, R.color.texto_agregado, R.color.texto_completado);
                detalle_cambiar_color(completado_txt3, R.color.texto_agregado, R.color.texto_completado);
                detalle_cambiar_color(completado_txt4, R.color.texto_agregado, R.color.texto_completado);
                detalle_cambiar_color(completado_txt5, R.color.texto_agregado, R.color.texto_completado);
                detalle_cambiar_color(completado_txt6, R.color.texto_agregado, R.color.texto_completado);
                detalle_cambiar_color(completado_txt7, R.color.texto_agregado, R.color.texto_completado);
                detalle_cambiar_color(completado_txt8, R.color.texto_agregado, R.color.texto_completado);
                detalle_cambiar_color(completado_txt9, R.color.texto_agregado, R.color.texto_completado);
                detalle_cambiar_color(completado_txt10, R.color.texto_agregado, R.color.texto_completado);
                detalle_cambiar_color(completado_txt11, R.color.texto_agregado, R.color.texto_completado);
                detalle_cambiar_color(completado_txt12, R.color.texto_agregado, R.color.texto_completado);
                detalle_cambiar_color(completado_txt13, R.color.texto_agregado, R.color.texto_completado);
                detalle_cambiar_color(completado_txt14, R.color.texto_agregado, R.color.texto_completado);
                detalle_cambiar_color(completado_txt15, R.color.texto_agregado, R.color.texto_completado);
                break;
            case "rojo":
                detalle_cambiar_color(completado_txt1, R.color.texto_agregado, R.color.texto_incorrecto);
                detalle_cambiar_color(completado_txt2, R.color.texto_agregado, R.color.texto_incorrecto);
                detalle_cambiar_color(completado_txt3, R.color.texto_agregado, R.color.texto_incorrecto);
                detalle_cambiar_color(completado_txt4, R.color.texto_agregado, R.color.texto_incorrecto);
                detalle_cambiar_color(completado_txt5, R.color.texto_agregado, R.color.texto_incorrecto);
                detalle_cambiar_color(completado_txt6, R.color.texto_agregado, R.color.texto_incorrecto);
                detalle_cambiar_color(completado_txt7, R.color.texto_agregado, R.color.texto_incorrecto);
                detalle_cambiar_color(completado_txt8, R.color.texto_agregado, R.color.texto_incorrecto);
                detalle_cambiar_color(completado_txt9, R.color.texto_agregado, R.color.texto_incorrecto);
                detalle_cambiar_color(completado_txt10, R.color.texto_agregado, R.color.texto_incorrecto);
                detalle_cambiar_color(completado_txt11, R.color.texto_agregado, R.color.texto_incorrecto);
                detalle_cambiar_color(completado_txt12, R.color.texto_agregado, R.color.texto_incorrecto);
                detalle_cambiar_color(completado_txt13, R.color.texto_agregado, R.color.texto_incorrecto);
                detalle_cambiar_color(completado_txt14, R.color.texto_agregado, R.color.texto_incorrecto);
                detalle_cambiar_color(completado_txt15, R.color.texto_agregado, R.color.texto_incorrecto);
        }
    }

    //Función cambiar color
    private void detalle_cambiar_color(TextView texto_color, int color_get, int color_set) {
        if (!(texto_color.getCurrentTextColor() == ContextCompat.getColor(this, color_get)))
        texto_color.setTextColor(ContextCompat.getColor(this, color_set));
    }

    //Cambiar intent segun nivel
    private void cambiar_nivel(String sonido) {
        detalle_sonido(sonido);
        funcion_herramienta_agregar(0);
        funcion_herramienta_eliminar(1);
        modificarOroSegunNivel();
        Intent intent = new Intent(Nivel2.this, respuesta_nivel2.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        if (actual_lvl % 5 == 0 ) if (mInterstitialAd != null) mInterstitialAd.show(Nivel2.this);

    }

    //Cargar Sonidos
    private void sonidos() {
        if(sp!=null){sp.release();}
        sp = new SoundPool.Builder().setMaxStreams(10).build();
        sound_abrir = sp.load(this, R.raw.abrir,1);
        sound_btn2 = sp.load(this, R.raw.boton2,1);
        sound_btn1 = sp.load(this, R.raw.boton1,1);
        sound_herramientas = sp.load(this, R.raw.herramientas,1);
        sound_dialog = sp.load(this, R.raw.show_dialog,1);
        sound_incorrecto = sp.load(this, R.raw.incorrecto,1);
        sound_ganar = sp.load(this, R.raw.ganar,1);
    }

    //Reproducir sonidos
    private void detalle_sonido(String tipo) {
        if (valor_volumen==1) {
            switch (tipo) {
                case "abrir":
                    sp.play(sound_abrir, 1,1,0,0,1);
                    break;
                case "boton1":
                    detalle_sonido_boton1();
                    break;
                case "boton2":
                    sp.play(sound_btn2, 1,1,0,0,1);
                    break;
                case "herramientas":
                    sp.play(sound_herramientas, 1,0,1,0,1);
                    break;
                case "dialog":
                    sp.play(sound_dialog, 1,1,0,0,1);
                    break;
                case "incorrecto":
                    sp.play(sound_incorrecto, 1,1,0,0,1);
                    break;
                case "ganar":
                    sp.play(sound_ganar, 1,1,0,0,1);
                    break;
            }
        }
    }

    //No reproducir el sonido cuando tenga todas las letras "completado"
    private void detalle_sonido_boton1() {
        if (!(completado_txt15.getCurrentTextColor() == ContextCompat.getColor(this, R.color.texto_incorrecto))) {
            sp.play(sound_btn1, 1,1,1,0,1);
        }
    }

    //Esconder UI
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

    //Cambiar color y mover el layout al estar lleno e incorrecto "completado"
    private void texto_completo_incorrecto() {
        detalle_sonido("incorrecto");
        Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
        parent_completado.startAnimation(shake);
        cambiar_color("rojo");
    }

    //Ir al dialog de aumento de monedas
    private void click_monedas() {
        detalle_sonido("dialog");
        dialog_agregar_monedas();
    }

    //Cargar la herramienta eliminar al volver a ingresar si esta presionado
    private void herramienta_eliminar_presionado() {
        if (herramienta_eliminar == 2 || contador_eliminar == 2) {
            eliminar_letras_incorrectas();
            btnEliminar.setBackgroundColor(Color.parseColor("#5A5F75"));
            btnEliminar.setEnabled(false);
        }
    }

    //Cambiar el valor deñ sharePreference eliminar, si esta en 2 significa que esta presionado, si esta en 1 no lo esta
    private void funcion_herramienta_eliminar(int valor) {
        SharedPreferences sp = this.getSharedPreferences("herramientas", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(getString(R.string.eliminar), valor);
        editor.apply();
    }

    //Similar a la herramienta eliminar solo que el valor indica la cantidad de letras completadas
    private void funcion_herramienta_agregar(int valor) {
        SharedPreferences sp = this.getSharedPreferences("herramientas", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(getString(R.string.agregar), valor);
        editor.apply();
    }

    //Al limpiar "completados" impedir que se limpien las letras añadidas con la herramienta agregar
    private void detalle_limpiar_completado(TextView texto_color) {
        if (!(texto_color.getCurrentTextColor() == ContextCompat.getColor(this, R.color.texto_agregado)))
        {texto_color.setVisibility(View.INVISIBLE);}
    }

    //Mostrar la letra al presionar la herramienta agregar
    private void detalle_herramienta_agregar_MostrarLetra(TextView lugar, String letra) {
        lugar.setText(letra);
        lugar.setVisibility(View.VISIBLE);
        lugar.setTextColor(ContextCompat.getColor(this, R.color.texto_agregado));
        lugar.setEnabled(false);
    }

    private void mostrar_ocultar_opcion(int tipo, LinearLayout opcion) {
        switch (tipo) {
            case 1:
                opcion.setVisibility(View.VISIBLE);
                break;
            case 2:
                opcion.setVisibility(View.GONE);
                break;
        }
    }

    private void mostrar_ocultar_opciones_completo(int tipo) {
        mostrar_ocultar_opcion(tipo, sup_1);
        mostrar_ocultar_opcion(tipo, sup_2);
        mostrar_ocultar_opcion(tipo, sup_3);
        mostrar_ocultar_opcion(tipo, sup_4);
        mostrar_ocultar_opcion(tipo, sup_5);
        mostrar_ocultar_opcion(tipo, sup_6);
        mostrar_ocultar_opcion(tipo, sup_7);
        mostrar_ocultar_opcion(tipo, sup_8);
        mostrar_ocultar_opcion(tipo, inf_1);
        mostrar_ocultar_opcion(tipo, inf_2);
        mostrar_ocultar_opcion(tipo, inf_3);
        mostrar_ocultar_opcion(tipo, inf_4);
        mostrar_ocultar_opcion(tipo, inf_5);
        mostrar_ocultar_opcion(tipo, inf_6);
        mostrar_ocultar_opcion(tipo, inf_7);
        mostrar_ocultar_opcion(tipo, inf_8);
    }

    //Funcion para ocultar en "opciones" las letras agregadas con la herramienta agregar
    private LinearLayout add_letra_ocultar(String letra) {
        String texto1 = (String) opcion_txt1.getText();
        String texto2 = (String) opcion_txt2.getText();
        String texto3 = (String) opcion_txt3.getText();
        String texto4 = (String) opcion_txt4.getText();
        String texto5 = (String) opcion_txt5.getText();
        String texto6 = (String) opcion_txt6.getText();
        String texto7 = (String) opcion_txt7.getText();
        String texto8 = (String) opcion_txt8.getText();
        String texto9 = (String) opcion_txt9.getText();
        String texto10 = (String) opcion_txt10.getText();
        String texto11 = (String) opcion_txt11.getText();
        String texto12 = (String) opcion_txt12.getText();
        String texto13 = (String) opcion_txt13.getText();
        String texto14 = (String) opcion_txt14.getText();
        String texto15 = (String) opcion_txt15.getText();
        String texto16 = (String) opcion_txt16.getText();
        if (texto1.equals(letra)) {
            return sup_1;
        } else if (texto2.equals(letra)) {
            return sup_2;
        } else if (texto3.equals(letra)) {
            return sup_3;
        } else if (texto4.equals(letra)) {
            return sup_4;
        } else if (texto5.equals(letra)) {
            return sup_5;
        } else if (texto6.equals(letra)) {
            return sup_6;
        } else if (texto7.equals(letra)) {
            return sup_7;
        } else if (texto8.equals(letra)) {
            return sup_8;
        } else if (texto9.equals(letra)) {
            return inf_1;
        } else if (texto10.equals(letra)) {
            return inf_2;
        } else if (texto11.equals(letra)) {
            return inf_3;
        } else if (texto12.equals(letra)) {
            return inf_4;
        } else if (texto13.equals(letra)) {
            return inf_5;
        } else if (texto14.equals(letra)) {
            return inf_6;
        } else if (texto15.equals(letra)) {
            return inf_7;
        } else if (texto16.equals(letra)) {
            return inf_8;
        } else {
            return sup_1;
        }
    }

    //Fucion para ocultar las letras incorrectas en "opciones" al presionar la herramienta ocultar
    private void ocultar_letras_incorrectas() {
        mostrar_ocultar_opciones_completo(2);
        char[] caracter = palabra_principal.toCharArray();
        String texto1 = (String) opcion_txt1.getText();
        String texto2 = (String) opcion_txt2.getText();
        String texto3 = (String) opcion_txt3.getText();
        String texto4 = (String) opcion_txt4.getText();
        String texto5 = (String) opcion_txt5.getText();
        String texto6 = (String) opcion_txt6.getText();
        String texto7 = (String) opcion_txt7.getText();
        String texto8 = (String) opcion_txt8.getText();
        String texto9 = (String) opcion_txt9.getText();
        String texto10 = (String) opcion_txt10.getText();
        String texto11 = (String) opcion_txt11.getText();
        String texto12 = (String) opcion_txt12.getText();
        String texto13 = (String) opcion_txt13.getText();
        String texto14 = (String) opcion_txt14.getText();
        String texto15 = (String) opcion_txt15.getText();
        String texto16 = (String) opcion_txt16.getText();
        for (char c : caracter) {
            if (texto1.equals(String.valueOf(c))) {
                sup_1.setVisibility(View.VISIBLE);
                break;
            }
        }
        for (char c : caracter) {
            if (texto2.equals(String.valueOf(c))) {
                sup_2.setVisibility(View.VISIBLE);
                break;
            }
        }
        for (char c : caracter) {
            if (texto3.equals(String.valueOf(c))) {
                sup_3.setVisibility(View.VISIBLE);
                break;
            }
        }
        for (char c : caracter) {
            if (texto4.equals(String.valueOf(c))) {
                sup_4.setVisibility(View.VISIBLE);
                break;
            }
        }
        for (char c : caracter) {
            if (texto5.equals(String.valueOf(c))) {
                sup_5.setVisibility(View.VISIBLE);
                break;
            }
        }
        for (char c : caracter) {
            if (texto6.equals(String.valueOf(c))) {
                sup_6.setVisibility(View.VISIBLE);
                break;
            }
        }
        for (char c : caracter) {
            if (texto7.equals(String.valueOf(c))) {
                sup_7.setVisibility(View.VISIBLE);
                break;
            }
        }
        for (char c : caracter) {
            if (texto8.equals(String.valueOf(c))) {
                sup_8.setVisibility(View.VISIBLE);
                break;
            }
        }
        for (char c : caracter) {
            if (texto9.equals(String.valueOf(c))) {
                inf_1.setVisibility(View.VISIBLE);
                break;
            }
        }
        for (char c : caracter) {
            if (texto10.equals(String.valueOf(c))) {
                inf_2.setVisibility(View.VISIBLE);
                break;
            }
        }
        for (char c : caracter) {
            if (texto11.equals(String.valueOf(c))) {
                inf_3.setVisibility(View.VISIBLE);
                break;
            }
        }
        for (char c : caracter) {
            if (texto12.equals(String.valueOf(c))) {
                inf_4.setVisibility(View.VISIBLE);
                break;
            }
        }
        for (char c : caracter) {
            if (texto13.equals(String.valueOf(c))) {
                inf_5.setVisibility(View.VISIBLE);
                break;
            }
        }
        for (char c : caracter) {
            if (texto14.equals(String.valueOf(c))) {
                inf_6.setVisibility(View.VISIBLE);
                break;
            }
        }
        for (char c : caracter) {
            if (texto15.equals(String.valueOf(c))) {
                inf_7.setVisibility(View.VISIBLE);
                break;
            }
        }
        for (char c : caracter) {
            if (texto16.equals(String.valueOf(c))) {
                inf_8.setVisibility(View.VISIBLE);
                break;
            }
        }
    }

    private void detalle_add_letra_ocultar() {
        char[] caracter = palabra_principal.toCharArray();
        switch (contador_agregar) {
            case 1:
                add_letra_ocultar(String.valueOf(caracter[0])).setVisibility(View.GONE);
                break;
            case 2:
                add_letra_ocultar(String.valueOf(caracter[0])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[1])).setVisibility(View.GONE);
                break;
            case 3:
                add_letra_ocultar(String.valueOf(caracter[0])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[1])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[2])).setVisibility(View.GONE);
                break;
            case 4:
                add_letra_ocultar(String.valueOf(caracter[0])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[1])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[2])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[3])).setVisibility(View.GONE);
                break;
            case 5:
                add_letra_ocultar(String.valueOf(caracter[0])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[1])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[2])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[3])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[4])).setVisibility(View.GONE);
                break;
            case 6:
                add_letra_ocultar(String.valueOf(caracter[0])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[1])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[2])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[3])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[4])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[5])).setVisibility(View.GONE);
                break;
            case 7:
                add_letra_ocultar(String.valueOf(caracter[0])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[1])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[2])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[3])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[4])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[5])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[6])).setVisibility(View.GONE);
                break;
            case 8:
                add_letra_ocultar(String.valueOf(caracter[0])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[1])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[2])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[3])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[4])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[5])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[6])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[7])).setVisibility(View.GONE);
                break;
            case 9:
                add_letra_ocultar(String.valueOf(caracter[0])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[1])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[2])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[3])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[4])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[5])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[6])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[7])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[8])).setVisibility(View.GONE);
                break;
            case 10:
                add_letra_ocultar(String.valueOf(caracter[0])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[1])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[2])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[3])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[4])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[5])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[6])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[7])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[8])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[9])).setVisibility(View.GONE);
                break;
            case 11:
                add_letra_ocultar(String.valueOf(caracter[0])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[1])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[2])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[3])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[4])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[5])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[6])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[7])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[8])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[9])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[10])).setVisibility(View.GONE);
                break;
            case 12:
                add_letra_ocultar(String.valueOf(caracter[0])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[1])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[2])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[3])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[4])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[5])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[6])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[7])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[8])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[9])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[10])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[11])).setVisibility(View.GONE);
                break;
            case 13:
                add_letra_ocultar(String.valueOf(caracter[0])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[1])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[2])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[3])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[4])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[5])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[6])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[7])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[8])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[9])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[10])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[11])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[12])).setVisibility(View.GONE);
                break;
            case 14:
                add_letra_ocultar(String.valueOf(caracter[0])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[1])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[2])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[3])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[4])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[5])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[6])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[7])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[8])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[9])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[10])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[11])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[12])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[13])).setVisibility(View.GONE);
                break;
            case 15:
                add_letra_ocultar(String.valueOf(caracter[0])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[1])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[2])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[3])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[4])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[5])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[6])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[7])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[8])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[9])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[10])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[11])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[12])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[13])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[14])).setVisibility(View.GONE);
                break;
            case 16:
                add_letra_ocultar(String.valueOf(caracter[0])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[1])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[2])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[3])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[4])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[5])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[6])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[7])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[8])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[9])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[10])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[11])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[12])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[13])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[14])).setVisibility(View.GONE);
                add_letra_ocultar(String.valueOf(caracter[15])).setVisibility(View.GONE);
                break;
        }
    }

    private void click_herramientas() {
        btnAgregar.setOnClickListener(view -> {
            if (gold < 50) {
                click_monedas();
            } else {
                detalle_sonido("dialog");
                click_btnAgregar();
            }
        });

        btnEliminar.setOnClickListener(view -> {
            if (gold < 150) {
                click_monedas();
            } else {
                detalle_sonido("dialog");
                click_btnEliminar();
            }
        });

        btnPasar.setOnClickListener(view -> {
            if (gold < 200) {
                click_monedas();
            } else {
                detalle_sonido("dialog");
                click_btnPasar();
            }
        });
    }

    private void click_btnAgregar() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_herramientas);
        FloatingActionButton mDialogNo = dialog.findViewById(R.id.frmNo);
        FloatingActionButton mDialogSi = dialog.findViewById(R.id.frmOk);
        TextView txtCuerpo = dialog.findViewById(R.id.txtCuerpo);
        txtCuerpo.setText(getResources().getString(R.string.texto_herramienta_agregar));
        mDialogSi.setOnClickListener(view -> {
            detalle_sonido("herramientas");
            contador_agregar++;
            herramienta_agregar_letra();
            funcion_herramienta_agregar(contador_agregar);
            herramienta_reducir_puntos(50);
            dialog.dismiss();
        });
        mDialogNo.setOnClickListener(view -> {
            detalle_sonido("dialog");
            dialog.dismiss();
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        if (dialog.getWindow() != null){
            dialog.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;
        }
        dialog.show();
        dialog.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        btnAgregar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnPasar.setEnabled(false);
        new Handler().postDelayed(() -> {
            btnAgregar.setEnabled(true);
            btnEliminar.setEnabled(true);
            btnPasar.setEnabled(true);
        }, 1000);
    }

    private void click_btnEliminar() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_herramientas);
        FloatingActionButton mDialogNo = dialog.findViewById(R.id.frmNo);
        FloatingActionButton mDialogSi = dialog.findViewById(R.id.frmOk);
        TextView txtCuerpo = dialog.findViewById(R.id.txtCuerpo);
        txtCuerpo.setText(getResources().getString(R.string.texto_herramienta_eliminar));
        mDialogSi.setOnClickListener(view -> {
            detalle_sonido("herramientas");
            contador_eliminar++;
            eliminar_letras_incorrectas();
            herramienta_reducir_puntos(150);
            btnEliminar.setBackgroundColor(Color.parseColor("#5A5F75"));
            btnEliminar.setEnabled(false);
            dialog.dismiss();
        });
        mDialogNo.setOnClickListener(view -> {
            detalle_sonido("dialog");
            dialog.dismiss();
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        if (dialog.getWindow() != null){
            dialog.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;
        }
        dialog.show();
        dialog.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        btnAgregar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnPasar.setEnabled(false);
        new Handler().postDelayed(() -> {
            btnAgregar.setEnabled(true);
            btnEliminar.setEnabled(true);
            btnPasar.setEnabled(true);
        }, 1000);
    }

    private void click_btnPasar() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_herramientas);
        FloatingActionButton mDialogNo = dialog.findViewById(R.id.frmNo);
        FloatingActionButton mDialogSi = dialog.findViewById(R.id.frmOk);
        TextView txtCuerpo = dialog.findViewById(R.id.txtCuerpo);
        txtCuerpo.setText(getResources().getString(R.string.texto_herramienta_pasar));
        mDialogSi.setOnClickListener(view -> {
            cambiar_nivel("herramientas");
            if (max_lvl == 6) {
                herramienta_reducir_puntos(200);
            } else {
                herramienta_reducir_puntos(195);
            }
            btnPasar.setBackgroundColor(Color.parseColor("#5A5F75"));
            btnPasar.setEnabled(false);
            dialog.dismiss();
        });
        mDialogNo.setOnClickListener(view -> {
            detalle_sonido("dialog");
            dialog.dismiss();
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        if (dialog.getWindow() != null){
            dialog.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;
        }
        dialog.show();
        dialog.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        btnAgregar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnPasar.setEnabled(false);
        new Handler().postDelayed(() -> {
            btnAgregar.setEnabled(true);
            btnEliminar.setEnabled(true);
            btnPasar.setEnabled(true);
        }, 1000);
    }

    private void shared_preference_herramientas() {
        SharedPreferences sp2 = this.getSharedPreferences("herramientas", Context.MODE_PRIVATE);
        int herramienta_agregar = sp2.getInt(getString(R.string.agregar), 0);
        herramienta_eliminar = sp2.getInt(getString(R.string.eliminar), 1);
        contador_agregar = herramienta_agregar;
    }

    private void herramienta_reducir_puntos(int puntos) {
        int new_oro = Integer.parseInt((String) btnMonedas.getText()) - puntos;
        btnMonedas.setText(String.valueOf(new_oro));
        int recom_actualizado = gold - puntos;
        SharedPreferences sp = this.getSharedPreferences("shared", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(getString(R.string.recompensa), recom_actualizado);
        editor.apply();
    }

    private void eliminar_letras_incorrectas() {
        ocultar_letras_incorrectas();
        detalle_add_letra_ocultar();
        limpiar_completado();
        funcion_herramienta_eliminar(2);
    }

    private void texto_completo() {
        char[] caracter = palabra_principal.toCharArray();
        switch (numero_letras) {
            case "2":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE) {
                    //En caso el texto sea Correcto
                    if (completado_txt1.getText().equals(String.valueOf(caracter[0])) && completado_txt2.getText().equals(String.valueOf(caracter[1]))) {
                        cambiar_nivel("ganar");
                        //En caso el texto sea Incorrecto
                    } else {
                        texto_completo_incorrecto();
                    }
                }
                break;
            case "3":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE) {
                    //En caso el texto sea Correcto
                    if (completado_txt1.getText().equals(String.valueOf(caracter[0])) && completado_txt2.getText().equals(String.valueOf(caracter[1]))
                            && completado_txt3.getText().equals(String.valueOf(caracter[2]))) {
                        cambiar_nivel("ganar");
                        //En caso el texto sea Incorrecto
                    } else {
                        texto_completo_incorrecto();
                    }
                }
                break;
            case "4":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE) {
                    //En caso el texto sea Correcto
                    if (completado_txt1.getText().equals(String.valueOf(caracter[0])) && completado_txt2.getText().equals(String.valueOf(caracter[1]))
                            && completado_txt3.getText().equals(String.valueOf(caracter[2])) && completado_txt4.getText().equals(String.valueOf(caracter[3]))) {
                        cambiar_nivel("ganar");
                        //En caso el texto sea Incorrecto
                    } else {
                        texto_completo_incorrecto();
                    }
                }
                break;
            case "5":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt5.getVisibility() != View.INVISIBLE) {
                    //En caso el texto sea Correcto
                    if (completado_txt1.getText().equals(String.valueOf(caracter[0])) && completado_txt2.getText().equals(String.valueOf(caracter[1]))
                            && completado_txt3.getText().equals(String.valueOf(caracter[2])) && completado_txt4.getText().equals(String.valueOf(caracter[3]))
                            && completado_txt5.getText().equals(String.valueOf(caracter[4]))) {
                        cambiar_nivel("ganar");
                        //En caso el texto sea Incorrecto
                    } else {
                        texto_completo_incorrecto();
                    }
                }
                break;
            case "6":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt5.getVisibility() != View.INVISIBLE && completado_txt6.getVisibility() != View.INVISIBLE) {
                    //En caso el texto sea Correcto
                    if (completado_txt1.getText().equals(String.valueOf(caracter[0])) && completado_txt2.getText().equals(String.valueOf(caracter[1]))
                            && completado_txt3.getText().equals(String.valueOf(caracter[2])) && completado_txt4.getText().equals(String.valueOf(caracter[3]))
                            && completado_txt5.getText().equals(String.valueOf(caracter[4])) && completado_txt6.getText().equals(String.valueOf(caracter[5]))) {
                        cambiar_nivel("ganar");
                        //En caso el texto sea Incorrecto
                    } else {
                        texto_completo_incorrecto();
                    }
                }
                break;
            case "7":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt5.getVisibility() != View.INVISIBLE && completado_txt6.getVisibility() != View.INVISIBLE
                        && completado_txt7.getVisibility() != View.INVISIBLE) {
                    //En caso el texto sea Correcto
                    if (completado_txt1.getText().equals(String.valueOf(caracter[0])) && completado_txt2.getText().equals(String.valueOf(caracter[1]))
                            && completado_txt3.getText().equals(String.valueOf(caracter[2])) && completado_txt4.getText().equals(String.valueOf(caracter[3]))
                            && completado_txt5.getText().equals(String.valueOf(caracter[4])) && completado_txt6.getText().equals(String.valueOf(caracter[5]))
                            && completado_txt7.getText().equals(String.valueOf(caracter[6]))) {
                        cambiar_nivel("ganar");
                        //En caso el texto sea Incorrecto
                    } else {
                        texto_completo_incorrecto();
                    }
                }
                break;
            case "8":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt5.getVisibility() != View.INVISIBLE && completado_txt6.getVisibility() != View.INVISIBLE
                        && completado_txt7.getVisibility() != View.INVISIBLE && completado_txt8.getVisibility() != View.INVISIBLE) {
                    //En caso el texto sea Correcto
                    if (completado_txt1.getText().equals(String.valueOf(caracter[0])) && completado_txt2.getText().equals(String.valueOf(caracter[1]))
                            && completado_txt3.getText().equals(String.valueOf(caracter[2])) && completado_txt4.getText().equals(String.valueOf(caracter[3]))
                            && completado_txt5.getText().equals(String.valueOf(caracter[4])) && completado_txt6.getText().equals(String.valueOf(caracter[5]))
                            && completado_txt7.getText().equals(String.valueOf(caracter[6])) && completado_txt8.getText().equals(String.valueOf(caracter[7]))) {
                        cambiar_nivel("ganar");
                        //En caso el texto sea Incorrecto
                    } else {
                        texto_completo_incorrecto();
                    }
                }
                break;
            case "4-4":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt9.getVisibility() != View.INVISIBLE && completado_txt10.getVisibility() != View.INVISIBLE
                        && completado_txt11.getVisibility() != View.INVISIBLE && completado_txt12.getVisibility() != View.INVISIBLE) {
                    //En caso el texto sea Correcto
                    if (completado_txt1.getText().equals(String.valueOf(caracter[0])) && completado_txt2.getText().equals(String.valueOf(caracter[1]))
                            && completado_txt3.getText().equals(String.valueOf(caracter[2])) && completado_txt4.getText().equals(String.valueOf(caracter[3]))
                            && completado_txt9.getText().equals(String.valueOf(caracter[4])) && completado_txt10.getText().equals(String.valueOf(caracter[5]))
                            && completado_txt11.getText().equals(String.valueOf(caracter[6])) && completado_txt12.getText().equals(String.valueOf(caracter[7]))) {
                        cambiar_nivel("ganar");
                        //En caso el texto sea Incorrecto
                    } else {
                        texto_completo_incorrecto();
                    }
                }
                break;
            case "4-5":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt9.getVisibility() != View.INVISIBLE && completado_txt10.getVisibility() != View.INVISIBLE
                        && completado_txt11.getVisibility() != View.INVISIBLE && completado_txt12.getVisibility() != View.INVISIBLE
                        && completado_txt13.getVisibility() != View.INVISIBLE) {
                    //En caso el texto sea Correcto
                    if (completado_txt1.getText().equals(String.valueOf(caracter[0])) && completado_txt2.getText().equals(String.valueOf(caracter[1]))
                            && completado_txt3.getText().equals(String.valueOf(caracter[2])) && completado_txt4.getText().equals(String.valueOf(caracter[3]))
                            && completado_txt9.getText().equals(String.valueOf(caracter[4])) && completado_txt10.getText().equals(String.valueOf(caracter[5]))
                            && completado_txt11.getText().equals(String.valueOf(caracter[6])) && completado_txt12.getText().equals(String.valueOf(caracter[7]))
                            && completado_txt13.getText().equals(String.valueOf(caracter[8]))) {
                        cambiar_nivel("ganar");
                        //En caso el texto sea Incorrecto
                    } else {
                        texto_completo_incorrecto();
                    }
                }
                break;
            case "4-6":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt9.getVisibility() != View.INVISIBLE && completado_txt10.getVisibility() != View.INVISIBLE
                        && completado_txt11.getVisibility() != View.INVISIBLE && completado_txt12.getVisibility() != View.INVISIBLE
                        && completado_txt13.getVisibility() != View.INVISIBLE && completado_txt14.getVisibility() != View.INVISIBLE) {
                    //En caso el texto sea Correcto
                    if (completado_txt1.getText().equals(String.valueOf(caracter[0])) && completado_txt2.getText().equals(String.valueOf(caracter[1]))
                            && completado_txt3.getText().equals(String.valueOf(caracter[2])) && completado_txt4.getText().equals(String.valueOf(caracter[3]))
                            && completado_txt9.getText().equals(String.valueOf(caracter[4])) && completado_txt10.getText().equals(String.valueOf(caracter[5]))
                            && completado_txt11.getText().equals(String.valueOf(caracter[6])) && completado_txt12.getText().equals(String.valueOf(caracter[7]))
                            && completado_txt13.getText().equals(String.valueOf(caracter[8])) && completado_txt14.getText().equals(String.valueOf(caracter[9]))) {
                        cambiar_nivel("ganar");
                        //En caso el texto sea Incorrecto
                    } else {
                        texto_completo_incorrecto();
                    }
                }
                break;
            case "4-7":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt9.getVisibility() != View.INVISIBLE && completado_txt10.getVisibility() != View.INVISIBLE
                        && completado_txt11.getVisibility() != View.INVISIBLE && completado_txt12.getVisibility() != View.INVISIBLE
                        && completado_txt13.getVisibility() != View.INVISIBLE && completado_txt14.getVisibility() != View.INVISIBLE
                        && completado_txt15.getVisibility() != View.INVISIBLE) {
                    //En caso el texto sea Correcto
                    if (completado_txt1.getText().equals(String.valueOf(caracter[0])) && completado_txt2.getText().equals(String.valueOf(caracter[1]))
                            && completado_txt3.getText().equals(String.valueOf(caracter[2])) && completado_txt4.getText().equals(String.valueOf(caracter[3]))
                            && completado_txt9.getText().equals(String.valueOf(caracter[4])) && completado_txt10.getText().equals(String.valueOf(caracter[5]))
                            && completado_txt11.getText().equals(String.valueOf(caracter[6])) && completado_txt12.getText().equals(String.valueOf(caracter[7]))
                            && completado_txt13.getText().equals(String.valueOf(caracter[8])) && completado_txt14.getText().equals(String.valueOf(caracter[9]))
                            && completado_txt15.getText().equals(String.valueOf(caracter[10]))) {
                        cambiar_nivel("ganar");
                        //En caso el texto sea Incorrecto
                    } else {
                        texto_completo_incorrecto();
                    }
                }
                break;
            case "5-4":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt5.getVisibility() != View.INVISIBLE
                        && completado_txt9.getVisibility() != View.INVISIBLE && completado_txt10.getVisibility() != View.INVISIBLE
                        && completado_txt11.getVisibility() != View.INVISIBLE && completado_txt12.getVisibility() != View.INVISIBLE) {
                    //En caso el texto sea Correcto
                    if (completado_txt1.getText().equals(String.valueOf(caracter[0])) && completado_txt2.getText().equals(String.valueOf(caracter[1]))
                            && completado_txt3.getText().equals(String.valueOf(caracter[2])) && completado_txt4.getText().equals(String.valueOf(caracter[3]))
                            && completado_txt5.getText().equals(String.valueOf(caracter[4]))
                            && completado_txt9.getText().equals(String.valueOf(caracter[5])) && completado_txt10.getText().equals(String.valueOf(caracter[6]))
                            && completado_txt11.getText().equals(String.valueOf(caracter[7])) && completado_txt12.getText().equals(String.valueOf(caracter[8]))) {
                        cambiar_nivel("ganar");
                        //En caso el texto sea Incorrecto
                    } else {
                        texto_completo_incorrecto();
                    }
                }
                break;
            case "5-5":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt5.getVisibility() != View.INVISIBLE
                        && completado_txt9.getVisibility() != View.INVISIBLE && completado_txt10.getVisibility() != View.INVISIBLE
                        && completado_txt11.getVisibility() != View.INVISIBLE && completado_txt12.getVisibility() != View.INVISIBLE
                        && completado_txt13.getVisibility() != View.INVISIBLE) {
                    //En caso el texto sea Correcto
                    if (completado_txt1.getText().equals(String.valueOf(caracter[0])) && completado_txt2.getText().equals(String.valueOf(caracter[1]))
                            && completado_txt3.getText().equals(String.valueOf(caracter[2])) && completado_txt4.getText().equals(String.valueOf(caracter[3]))
                            && completado_txt5.getText().equals(String.valueOf(caracter[4]))
                            && completado_txt9.getText().equals(String.valueOf(caracter[5])) && completado_txt10.getText().equals(String.valueOf(caracter[6]))
                            && completado_txt11.getText().equals(String.valueOf(caracter[7])) && completado_txt12.getText().equals(String.valueOf(caracter[8]))
                            && completado_txt13.getText().equals(String.valueOf(caracter[9]))) {
                        cambiar_nivel("ganar");
                        //En caso el texto sea Incorrecto
                    } else {
                        texto_completo_incorrecto();
                    }
                }
                break;
            case "5-6":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt5.getVisibility() != View.INVISIBLE
                        && completado_txt9.getVisibility() != View.INVISIBLE && completado_txt10.getVisibility() != View.INVISIBLE
                        && completado_txt11.getVisibility() != View.INVISIBLE && completado_txt12.getVisibility() != View.INVISIBLE
                        && completado_txt13.getVisibility() != View.INVISIBLE && completado_txt14.getVisibility() != View.INVISIBLE) {
                    //En caso el texto sea Correcto
                    if (completado_txt1.getText().equals(String.valueOf(caracter[0])) && completado_txt2.getText().equals(String.valueOf(caracter[1]))
                            && completado_txt3.getText().equals(String.valueOf(caracter[2])) && completado_txt4.getText().equals(String.valueOf(caracter[3]))
                            && completado_txt5.getText().equals(String.valueOf(caracter[4]))
                            && completado_txt9.getText().equals(String.valueOf(caracter[5])) && completado_txt10.getText().equals(String.valueOf(caracter[6]))
                            && completado_txt11.getText().equals(String.valueOf(caracter[7])) && completado_txt12.getText().equals(String.valueOf(caracter[8]))
                            && completado_txt13.getText().equals(String.valueOf(caracter[9])) && completado_txt14.getText().equals(String.valueOf(caracter[10]))) {
                        cambiar_nivel("ganar");
                        //En caso el texto sea Incorrecto
                    } else {
                        texto_completo_incorrecto();
                    }
                }
                break;
            case "5-7":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt5.getVisibility() != View.INVISIBLE
                        && completado_txt9.getVisibility() != View.INVISIBLE && completado_txt10.getVisibility() != View.INVISIBLE
                        && completado_txt11.getVisibility() != View.INVISIBLE && completado_txt12.getVisibility() != View.INVISIBLE
                        && completado_txt13.getVisibility() != View.INVISIBLE && completado_txt14.getVisibility() != View.INVISIBLE
                        && completado_txt15.getVisibility() != View.INVISIBLE) {
                    //En caso el texto sea Correcto
                    if (completado_txt1.getText().equals(String.valueOf(caracter[0])) && completado_txt2.getText().equals(String.valueOf(caracter[1]))
                            && completado_txt3.getText().equals(String.valueOf(caracter[2])) && completado_txt4.getText().equals(String.valueOf(caracter[3]))
                            && completado_txt5.getText().equals(String.valueOf(caracter[4]))
                            && completado_txt9.getText().equals(String.valueOf(caracter[5])) && completado_txt10.getText().equals(String.valueOf(caracter[6]))
                            && completado_txt11.getText().equals(String.valueOf(caracter[7])) && completado_txt12.getText().equals(String.valueOf(caracter[8]))
                            && completado_txt13.getText().equals(String.valueOf(caracter[9])) && completado_txt14.getText().equals(String.valueOf(caracter[10]))
                            && completado_txt15.getText().equals(String.valueOf(caracter[11]))) {
                        cambiar_nivel("ganar");
                        //En caso el texto sea Incorrecto
                    } else {
                        texto_completo_incorrecto();
                    }
                }
                break;
            case "6-3":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt5.getVisibility() != View.INVISIBLE && completado_txt6.getVisibility() != View.INVISIBLE
                        && completado_txt9.getVisibility() != View.INVISIBLE && completado_txt10.getVisibility() != View.INVISIBLE
                        && completado_txt11.getVisibility() != View.INVISIBLE) {
                    //En caso el texto sea Correcto
                    if (completado_txt1.getText().equals(String.valueOf(caracter[0])) && completado_txt2.getText().equals(String.valueOf(caracter[1]))
                            && completado_txt3.getText().equals(String.valueOf(caracter[2])) && completado_txt4.getText().equals(String.valueOf(caracter[3]))
                            && completado_txt5.getText().equals(String.valueOf(caracter[4])) && completado_txt6.getText().equals(String.valueOf(caracter[5]))
                            && completado_txt9.getText().equals(String.valueOf(caracter[6])) && completado_txt10.getText().equals(String.valueOf(caracter[7]))
                            && completado_txt11.getText().equals(String.valueOf(caracter[8]))) {
                        cambiar_nivel("ganar");
                        //En caso el texto sea Incorrecto
                    } else {
                        texto_completo_incorrecto();
                    }
                }
                break;
            case "6-4":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt5.getVisibility() != View.INVISIBLE && completado_txt6.getVisibility() != View.INVISIBLE
                        && completado_txt9.getVisibility() != View.INVISIBLE && completado_txt10.getVisibility() != View.INVISIBLE
                        && completado_txt11.getVisibility() != View.INVISIBLE && completado_txt12.getVisibility() != View.INVISIBLE) {
                    //En caso el texto sea Correcto
                    if (completado_txt1.getText().equals(String.valueOf(caracter[0])) && completado_txt2.getText().equals(String.valueOf(caracter[1]))
                            && completado_txt3.getText().equals(String.valueOf(caracter[2])) && completado_txt4.getText().equals(String.valueOf(caracter[3]))
                            && completado_txt5.getText().equals(String.valueOf(caracter[4])) && completado_txt6.getText().equals(String.valueOf(caracter[5]))
                            && completado_txt9.getText().equals(String.valueOf(caracter[6])) && completado_txt10.getText().equals(String.valueOf(caracter[7]))
                            && completado_txt11.getText().equals(String.valueOf(caracter[8])) && completado_txt12.getText().equals(String.valueOf(caracter[9]))) {
                        cambiar_nivel("ganar");
                        //En caso el texto sea Incorrecto
                    } else {
                        texto_completo_incorrecto();
                    }
                }
                break;
            case "6-5":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt5.getVisibility() != View.INVISIBLE && completado_txt6.getVisibility() != View.INVISIBLE
                        && completado_txt9.getVisibility() != View.INVISIBLE && completado_txt10.getVisibility() != View.INVISIBLE
                        && completado_txt11.getVisibility() != View.INVISIBLE && completado_txt12.getVisibility() != View.INVISIBLE
                        && completado_txt13.getVisibility() != View.INVISIBLE) {
                    //En caso el texto sea Correcto
                    if (completado_txt1.getText().equals(String.valueOf(caracter[0])) && completado_txt2.getText().equals(String.valueOf(caracter[1]))
                            && completado_txt3.getText().equals(String.valueOf(caracter[2])) && completado_txt4.getText().equals(String.valueOf(caracter[3]))
                            && completado_txt5.getText().equals(String.valueOf(caracter[4])) && completado_txt6.getText().equals(String.valueOf(caracter[5]))
                            && completado_txt9.getText().equals(String.valueOf(caracter[6])) && completado_txt10.getText().equals(String.valueOf(caracter[7]))
                            && completado_txt11.getText().equals(String.valueOf(caracter[8])) && completado_txt12.getText().equals(String.valueOf(caracter[9]))
                            && completado_txt13.getText().equals(String.valueOf(caracter[10]))) {
                        cambiar_nivel("ganar");
                        //En caso el texto sea Incorrecto
                    } else {
                        texto_completo_incorrecto();
                    }
                }
                break;
            case "6-6":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt5.getVisibility() != View.INVISIBLE && completado_txt6.getVisibility() != View.INVISIBLE
                        && completado_txt9.getVisibility() != View.INVISIBLE && completado_txt10.getVisibility() != View.INVISIBLE
                        && completado_txt11.getVisibility() != View.INVISIBLE && completado_txt12.getVisibility() != View.INVISIBLE
                        && completado_txt13.getVisibility() != View.INVISIBLE && completado_txt14.getVisibility() != View.INVISIBLE) {
                    //En caso el texto sea Correcto
                    if (completado_txt1.getText().equals(String.valueOf(caracter[0])) && completado_txt2.getText().equals(String.valueOf(caracter[1]))
                            && completado_txt3.getText().equals(String.valueOf(caracter[2])) && completado_txt4.getText().equals(String.valueOf(caracter[3]))
                            && completado_txt5.getText().equals(String.valueOf(caracter[4])) && completado_txt6.getText().equals(String.valueOf(caracter[5]))
                            && completado_txt9.getText().equals(String.valueOf(caracter[6])) && completado_txt10.getText().equals(String.valueOf(caracter[7]))
                            && completado_txt11.getText().equals(String.valueOf(caracter[8])) && completado_txt12.getText().equals(String.valueOf(caracter[9]))
                            && completado_txt13.getText().equals(String.valueOf(caracter[10])) && completado_txt14.getText().equals(String.valueOf(caracter[11]))) {
                        cambiar_nivel("ganar");
                        //En caso el texto sea Incorrecto
                    } else {
                        texto_completo_incorrecto();
                    }
                }
                break;
            case "7-4":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt5.getVisibility() != View.INVISIBLE && completado_txt6.getVisibility() != View.INVISIBLE
                        && completado_txt7.getVisibility() != View.INVISIBLE
                        && completado_txt9.getVisibility() != View.INVISIBLE && completado_txt10.getVisibility() != View.INVISIBLE
                        && completado_txt11.getVisibility() != View.INVISIBLE && completado_txt12.getVisibility() != View.INVISIBLE) {
                    //En caso el texto sea Correcto
                    if (completado_txt1.getText().equals(String.valueOf(caracter[0])) && completado_txt2.getText().equals(String.valueOf(caracter[1]))
                            && completado_txt3.getText().equals(String.valueOf(caracter[2])) && completado_txt4.getText().equals(String.valueOf(caracter[3]))
                            && completado_txt5.getText().equals(String.valueOf(caracter[4])) && completado_txt6.getText().equals(String.valueOf(caracter[5]))
                            && completado_txt7.getText().equals(String.valueOf(caracter[6]))
                            && completado_txt9.getText().equals(String.valueOf(caracter[7])) && completado_txt10.getText().equals(String.valueOf(caracter[8]))
                            && completado_txt11.getText().equals(String.valueOf(caracter[9])) && completado_txt12.getText().equals(String.valueOf(caracter[10]))) {
                        cambiar_nivel("ganar");
                        //En caso el texto sea Incorrecto
                    } else {
                        texto_completo_incorrecto();
                    }
                }
                break;
            case "7-6":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt5.getVisibility() != View.INVISIBLE && completado_txt6.getVisibility() != View.INVISIBLE
                        && completado_txt7.getVisibility() != View.INVISIBLE
                        && completado_txt9.getVisibility() != View.INVISIBLE && completado_txt10.getVisibility() != View.INVISIBLE
                        && completado_txt11.getVisibility() != View.INVISIBLE && completado_txt12.getVisibility() != View.INVISIBLE
                        && completado_txt13.getVisibility() != View.INVISIBLE && completado_txt14.getVisibility() != View.INVISIBLE) {
                    //En caso el texto sea Correcto
                    if (completado_txt1.getText().equals(String.valueOf(caracter[0])) && completado_txt2.getText().equals(String.valueOf(caracter[1]))
                            && completado_txt3.getText().equals(String.valueOf(caracter[2])) && completado_txt4.getText().equals(String.valueOf(caracter[3]))
                            && completado_txt5.getText().equals(String.valueOf(caracter[4])) && completado_txt6.getText().equals(String.valueOf(caracter[5]))
                            && completado_txt7.getText().equals(String.valueOf(caracter[6]))
                            && completado_txt9.getText().equals(String.valueOf(caracter[7])) && completado_txt10.getText().equals(String.valueOf(caracter[8]))
                            && completado_txt11.getText().equals(String.valueOf(caracter[9])) && completado_txt12.getText().equals(String.valueOf(caracter[10]))
                            && completado_txt13.getText().equals(String.valueOf(caracter[11])) && completado_txt14.getText().equals(String.valueOf(caracter[12]))) {
                        cambiar_nivel("ganar");
                        //En caso el texto sea Incorrecto
                    } else {
                        texto_completo_incorrecto();
                    }
                }
                break;
            case "8-4":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt5.getVisibility() != View.INVISIBLE && completado_txt6.getVisibility() != View.INVISIBLE
                        && completado_txt7.getVisibility() != View.INVISIBLE && completado_txt8.getVisibility() != View.INVISIBLE
                        && completado_txt9.getVisibility() != View.INVISIBLE && completado_txt10.getVisibility() != View.INVISIBLE
                        && completado_txt11.getVisibility() != View.INVISIBLE && completado_txt12.getVisibility() != View.INVISIBLE) {
                    //En caso el texto sea Correcto
                    if (completado_txt1.getText().equals(String.valueOf(caracter[0])) && completado_txt2.getText().equals(String.valueOf(caracter[1]))
                            && completado_txt3.getText().equals(String.valueOf(caracter[2])) && completado_txt4.getText().equals(String.valueOf(caracter[3]))
                            && completado_txt5.getText().equals(String.valueOf(caracter[4])) && completado_txt6.getText().equals(String.valueOf(caracter[5]))
                            && completado_txt7.getText().equals(String.valueOf(caracter[6])) && completado_txt8.getText().equals(String.valueOf(caracter[7]))
                            && completado_txt9.getText().equals(String.valueOf(caracter[8])) && completado_txt10.getText().equals(String.valueOf(caracter[9]))
                            && completado_txt11.getText().equals(String.valueOf(caracter[10])) && completado_txt12.getText().equals(String.valueOf(caracter[11]))) {
                        cambiar_nivel("ganar");
                        //En caso el texto sea Incorrecto
                    } else {
                        texto_completo_incorrecto();
                    }
                }
                break;
            case "8-6":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt5.getVisibility() != View.INVISIBLE && completado_txt6.getVisibility() != View.INVISIBLE
                        && completado_txt7.getVisibility() != View.INVISIBLE && completado_txt8.getVisibility() != View.INVISIBLE
                        && completado_txt9.getVisibility() != View.INVISIBLE && completado_txt10.getVisibility() != View.INVISIBLE
                        && completado_txt11.getVisibility() != View.INVISIBLE && completado_txt12.getVisibility() != View.INVISIBLE
                        && completado_txt13.getVisibility() != View.INVISIBLE && completado_txt14.getVisibility() != View.INVISIBLE) {
                    //En caso el texto sea Correcto
                    if (completado_txt1.getText().equals(String.valueOf(caracter[0])) && completado_txt2.getText().equals(String.valueOf(caracter[1]))
                            && completado_txt3.getText().equals(String.valueOf(caracter[2])) && completado_txt4.getText().equals(String.valueOf(caracter[3]))
                            && completado_txt5.getText().equals(String.valueOf(caracter[4])) && completado_txt6.getText().equals(String.valueOf(caracter[5]))
                            && completado_txt7.getText().equals(String.valueOf(caracter[6])) && completado_txt8.getText().equals(String.valueOf(caracter[7]))
                            && completado_txt9.getText().equals(String.valueOf(caracter[8])) && completado_txt10.getText().equals(String.valueOf(caracter[9]))
                            && completado_txt11.getText().equals(String.valueOf(caracter[10])) && completado_txt12.getText().equals(String.valueOf(caracter[11]))
                            && completado_txt13.getText().equals(String.valueOf(caracter[12])) && completado_txt14.getText().equals(String.valueOf(caracter[13]))) {
                        cambiar_nivel("ganar");
                        //En caso el texto sea Incorrecto
                    } else {
                        texto_completo_incorrecto();
                    }
                }
                break;
        }
    }

    private void cambio_txt() {
        completado_linea2.setVisibility(View.GONE);
        switch (numero_letras) {
            case "2":
                comp_sup_3.setVisibility(View.GONE);
                comp_sup_4.setVisibility(View.GONE);
                comp_sup_5.setVisibility(View.GONE);
                comp_sup_6.setVisibility(View.GONE);
                comp_sup_7.setVisibility(View.GONE);
                comp_sup_8.setVisibility(View.GONE);
                break;
            case "3":
                comp_sup_4.setVisibility(View.GONE);
                comp_sup_5.setVisibility(View.GONE);
                comp_sup_6.setVisibility(View.GONE);
                comp_sup_7.setVisibility(View.GONE);
                comp_sup_8.setVisibility(View.GONE);
                break;
            case "4":
                comp_sup_5.setVisibility(View.GONE);
                comp_sup_6.setVisibility(View.GONE);
                comp_sup_7.setVisibility(View.GONE);
                comp_sup_8.setVisibility(View.GONE);
                break;
            case "5":
                comp_sup_6.setVisibility(View.GONE);
                comp_sup_7.setVisibility(View.GONE);
                comp_sup_8.setVisibility(View.GONE);
                break;
            case "6":
                comp_sup_7.setVisibility(View.GONE);
                comp_sup_8.setVisibility(View.GONE);
                break;
            case "7":
                comp_sup_8.setVisibility(View.GONE);
                break;
            case "8":
                break;
            case "4-4":
                completado_linea2.setVisibility(View.VISIBLE);
                comp_sup_5.setVisibility(View.GONE);
                comp_sup_6.setVisibility(View.GONE);
                comp_sup_7.setVisibility(View.GONE);
                comp_sup_8.setVisibility(View.GONE);
                comp_inf_5.setVisibility(View.GONE);
                comp_inf_6.setVisibility(View.GONE);
                comp_inf_7.setVisibility(View.GONE);
                completado_txt5.setVisibility(View.GONE);
                completado_txt6.setVisibility(View.GONE);
                completado_txt7.setVisibility(View.GONE);
                completado_txt8.setVisibility(View.GONE);
                break;
            case "4-5":
                completado_linea2.setVisibility(View.VISIBLE);
                comp_sup_5.setVisibility(View.GONE);
                comp_sup_6.setVisibility(View.GONE);
                comp_sup_7.setVisibility(View.GONE);
                comp_sup_8.setVisibility(View.GONE);
                comp_inf_6.setVisibility(View.GONE);
                comp_inf_7.setVisibility(View.GONE);
                completado_txt5.setVisibility(View.GONE);
                completado_txt6.setVisibility(View.GONE);
                completado_txt7.setVisibility(View.GONE);
                completado_txt8.setVisibility(View.GONE);
                break;
            case "4-6":
                completado_linea2.setVisibility(View.VISIBLE);
                comp_sup_5.setVisibility(View.GONE);
                comp_sup_6.setVisibility(View.GONE);
                comp_sup_7.setVisibility(View.GONE);
                comp_sup_8.setVisibility(View.GONE);
                comp_inf_7.setVisibility(View.GONE);
                completado_txt5.setVisibility(View.GONE);
                completado_txt6.setVisibility(View.GONE);
                completado_txt7.setVisibility(View.GONE);
                completado_txt8.setVisibility(View.GONE);
                break;
            case "4-7":
                completado_linea2.setVisibility(View.VISIBLE);
                comp_sup_5.setVisibility(View.GONE);
                comp_sup_6.setVisibility(View.GONE);
                comp_sup_7.setVisibility(View.GONE);
                comp_sup_8.setVisibility(View.GONE);
                completado_txt5.setVisibility(View.GONE);
                completado_txt6.setVisibility(View.GONE);
                completado_txt7.setVisibility(View.GONE);
                completado_txt8.setVisibility(View.GONE);
                break;
            case "5-4":
                completado_linea2.setVisibility(View.VISIBLE);
                comp_sup_6.setVisibility(View.GONE);
                comp_sup_7.setVisibility(View.GONE);
                comp_sup_8.setVisibility(View.GONE);
                comp_inf_5.setVisibility(View.GONE);
                comp_inf_6.setVisibility(View.GONE);
                comp_inf_7.setVisibility(View.GONE);
                completado_txt6.setVisibility(View.GONE);
                completado_txt7.setVisibility(View.GONE);
                completado_txt8.setVisibility(View.GONE);
                break;
            case "5-5":
                completado_linea2.setVisibility(View.VISIBLE);
                comp_sup_6.setVisibility(View.GONE);
                comp_sup_7.setVisibility(View.GONE);
                comp_sup_8.setVisibility(View.GONE);
                comp_inf_6.setVisibility(View.GONE);
                comp_inf_7.setVisibility(View.GONE);
                completado_txt6.setVisibility(View.GONE);
                completado_txt7.setVisibility(View.GONE);
                completado_txt8.setVisibility(View.GONE);
                break;
            case "5-6":
                completado_linea2.setVisibility(View.VISIBLE);
                comp_sup_6.setVisibility(View.GONE);
                comp_sup_7.setVisibility(View.GONE);
                comp_sup_8.setVisibility(View.GONE);
                comp_inf_7.setVisibility(View.GONE);
                completado_txt6.setVisibility(View.GONE);
                completado_txt7.setVisibility(View.GONE);
                completado_txt8.setVisibility(View.GONE);
                break;
            case "5-7":
                completado_linea2.setVisibility(View.VISIBLE);
                comp_sup_6.setVisibility(View.GONE);
                comp_sup_7.setVisibility(View.GONE);
                comp_sup_8.setVisibility(View.GONE);
                completado_txt6.setVisibility(View.GONE);
                completado_txt7.setVisibility(View.GONE);
                completado_txt8.setVisibility(View.GONE);
                break;
            case "6-3":
                completado_linea2.setVisibility(View.VISIBLE);
                comp_sup_7.setVisibility(View.GONE);
                comp_sup_8.setVisibility(View.GONE);
                comp_inf_4.setVisibility(View.GONE);
                comp_inf_5.setVisibility(View.GONE);
                comp_inf_6.setVisibility(View.GONE);
                comp_inf_7.setVisibility(View.GONE);
                completado_txt7.setVisibility(View.GONE);
                completado_txt8.setVisibility(View.GONE);
                break;
            case "6-4":
                completado_linea2.setVisibility(View.VISIBLE);
                comp_sup_7.setVisibility(View.GONE);
                comp_sup_8.setVisibility(View.GONE);
                comp_inf_5.setVisibility(View.GONE);
                comp_inf_6.setVisibility(View.GONE);
                comp_inf_7.setVisibility(View.GONE);
                completado_txt7.setVisibility(View.GONE);
                completado_txt8.setVisibility(View.GONE);
                break;
            case "6-5":
                completado_linea2.setVisibility(View.VISIBLE);
                comp_sup_7.setVisibility(View.GONE);
                comp_sup_8.setVisibility(View.GONE);
                comp_inf_6.setVisibility(View.GONE);
                comp_inf_7.setVisibility(View.GONE);
                completado_txt7.setVisibility(View.GONE);
                completado_txt8.setVisibility(View.GONE);
                break;
            case "6-6":
                completado_linea2.setVisibility(View.VISIBLE);
                comp_sup_7.setVisibility(View.GONE);
                comp_sup_8.setVisibility(View.GONE);
                comp_inf_7.setVisibility(View.GONE);
                completado_txt7.setVisibility(View.GONE);
                completado_txt8.setVisibility(View.GONE);
                break;
            case "7-4":
                completado_linea2.setVisibility(View.VISIBLE);
                comp_sup_8.setVisibility(View.GONE);
                comp_inf_5.setVisibility(View.GONE);
                comp_inf_6.setVisibility(View.GONE);
                comp_inf_7.setVisibility(View.GONE);
                completado_txt8.setVisibility(View.GONE);
                break;
            case "7-6":
                completado_linea2.setVisibility(View.VISIBLE);
                comp_sup_8.setVisibility(View.GONE);
                comp_inf_7.setVisibility(View.GONE);
                completado_txt8.setVisibility(View.GONE);
                break;
            case "8-4":
                completado_linea2.setVisibility(View.VISIBLE);
                comp_inf_5.setVisibility(View.GONE);
                comp_inf_6.setVisibility(View.GONE);
                comp_inf_7.setVisibility(View.GONE);
                break;
            case "8-6":
                completado_linea2.setVisibility(View.VISIBLE);
                comp_inf_7.setVisibility(View.GONE);
                break;
        }
    }

    private void detalle_click_opciones(LinearLayout target, LinearLayout parent, TextView op_text) {
        detalle_sonido("boton1");
        switch (numero_letras) {
            case "2":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE) {
                } else {
                    det_detalle_click_opciones(target, parent, op_text);
                }
                break;
            case "3":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE) {
                } else {
                    det_detalle_click_opciones(target, parent, op_text);
                }
                break;
            case "4":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE) {
                } else {
                    det_detalle_click_opciones(target, parent, op_text);
                }
                break;
            case "5":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt5.getVisibility() != View.INVISIBLE) {
                } else {
                    det_detalle_click_opciones(target, parent, op_text);
                }
                break;
            case "6":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt5.getVisibility() != View.INVISIBLE && completado_txt6.getVisibility() != View.INVISIBLE) {
                } else {
                    det_detalle_click_opciones(target, parent, op_text);
                }
                break;
            case "7":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt5.getVisibility() != View.INVISIBLE && completado_txt6.getVisibility() != View.INVISIBLE
                        && completado_txt7.getVisibility() != View.INVISIBLE) {
                } else {
                    det_detalle_click_opciones(target, parent, op_text);
                }
                break;
            case "8":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt5.getVisibility() != View.INVISIBLE && completado_txt6.getVisibility() != View.INVISIBLE
                        && completado_txt7.getVisibility() != View.INVISIBLE && completado_txt8.getVisibility() != View.INVISIBLE) {
                } else {
                    det_detalle_click_opciones(target, parent, op_text);
                }
                break;
            case "4-4":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt9.getVisibility() != View.INVISIBLE && completado_txt10.getVisibility() != View.INVISIBLE
                        && completado_txt11.getVisibility() != View.INVISIBLE && completado_txt12.getVisibility() != View.INVISIBLE) {
                } else {
                    det_detalle_click_opciones(target, parent, op_text);
                }
                break;
            case "4-5":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt9.getVisibility() != View.INVISIBLE && completado_txt10.getVisibility() != View.INVISIBLE
                        && completado_txt11.getVisibility() != View.INVISIBLE && completado_txt12.getVisibility() != View.INVISIBLE
                        && completado_txt13.getVisibility() != View.INVISIBLE) {
                } else {
                    det_detalle_click_opciones(target, parent, op_text);
                }
                break;
            case "4-6":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt9.getVisibility() != View.INVISIBLE && completado_txt10.getVisibility() != View.INVISIBLE
                        && completado_txt11.getVisibility() != View.INVISIBLE && completado_txt12.getVisibility() != View.INVISIBLE
                        && completado_txt13.getVisibility() != View.INVISIBLE && completado_txt14.getVisibility() != View.INVISIBLE) {
                } else {
                    det_detalle_click_opciones(target, parent, op_text);
                }
                break;
            case "4-7":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt9.getVisibility() != View.INVISIBLE && completado_txt10.getVisibility() != View.INVISIBLE
                        && completado_txt11.getVisibility() != View.INVISIBLE && completado_txt12.getVisibility() != View.INVISIBLE
                        && completado_txt13.getVisibility() != View.INVISIBLE && completado_txt14.getVisibility() != View.INVISIBLE
                        && completado_txt15.getVisibility() != View.INVISIBLE) {
                } else {
                    det_detalle_click_opciones(target, parent, op_text);
                }
                break;
            case "5-4":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt5.getVisibility() != View.INVISIBLE
                        && completado_txt9.getVisibility() != View.INVISIBLE && completado_txt10.getVisibility() != View.INVISIBLE
                        && completado_txt11.getVisibility() != View.INVISIBLE && completado_txt12.getVisibility() != View.INVISIBLE) {
                } else {
                    det_detalle_click_opciones(target, parent, op_text);
                }
                break;
            case "5-5":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt5.getVisibility() != View.INVISIBLE
                        && completado_txt9.getVisibility() != View.INVISIBLE && completado_txt10.getVisibility() != View.INVISIBLE
                        && completado_txt11.getVisibility() != View.INVISIBLE && completado_txt12.getVisibility() != View.INVISIBLE
                        && completado_txt13.getVisibility() != View.INVISIBLE) {
                } else {
                    det_detalle_click_opciones(target, parent, op_text);
                }
                break;
            case "5-6":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt5.getVisibility() != View.INVISIBLE
                        && completado_txt9.getVisibility() != View.INVISIBLE && completado_txt10.getVisibility() != View.INVISIBLE
                        && completado_txt11.getVisibility() != View.INVISIBLE && completado_txt12.getVisibility() != View.INVISIBLE
                        && completado_txt13.getVisibility() != View.INVISIBLE && completado_txt14.getVisibility() != View.INVISIBLE) {
                } else {
                    det_detalle_click_opciones(target, parent, op_text);
                }
                break;
            case "5-7":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt5.getVisibility() != View.INVISIBLE
                        && completado_txt9.getVisibility() != View.INVISIBLE && completado_txt10.getVisibility() != View.INVISIBLE
                        && completado_txt11.getVisibility() != View.INVISIBLE && completado_txt12.getVisibility() != View.INVISIBLE
                        && completado_txt13.getVisibility() != View.INVISIBLE && completado_txt14.getVisibility() != View.INVISIBLE
                        && completado_txt15.getVisibility() != View.INVISIBLE) {
                } else {
                    det_detalle_click_opciones(target, parent, op_text);
                }
                break;
            case "6-3":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt5.getVisibility() != View.INVISIBLE && completado_txt6.getVisibility() != View.INVISIBLE
                        && completado_txt9.getVisibility() != View.INVISIBLE && completado_txt10.getVisibility() != View.INVISIBLE
                        && completado_txt11.getVisibility() != View.INVISIBLE) {
                } else {
                    det_detalle_click_opciones(target, parent, op_text);
                }
                break;
            case "6-4":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt5.getVisibility() != View.INVISIBLE && completado_txt6.getVisibility() != View.INVISIBLE
                        && completado_txt9.getVisibility() != View.INVISIBLE && completado_txt10.getVisibility() != View.INVISIBLE
                        && completado_txt11.getVisibility() != View.INVISIBLE && completado_txt12.getVisibility() != View.INVISIBLE) {
                } else {
                    det_detalle_click_opciones(target, parent, op_text);
                }
                break;
            case "6-5":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt5.getVisibility() != View.INVISIBLE && completado_txt6.getVisibility() != View.INVISIBLE
                        && completado_txt9.getVisibility() != View.INVISIBLE && completado_txt10.getVisibility() != View.INVISIBLE
                        && completado_txt11.getVisibility() != View.INVISIBLE && completado_txt12.getVisibility() != View.INVISIBLE
                        && completado_txt13.getVisibility() != View.INVISIBLE) {
                } else {
                    det_detalle_click_opciones(target, parent, op_text);
                }
                break;
            case "6-6":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt5.getVisibility() != View.INVISIBLE && completado_txt6.getVisibility() != View.INVISIBLE
                        && completado_txt9.getVisibility() != View.INVISIBLE && completado_txt10.getVisibility() != View.INVISIBLE
                        && completado_txt11.getVisibility() != View.INVISIBLE && completado_txt12.getVisibility() != View.INVISIBLE
                        && completado_txt13.getVisibility() != View.INVISIBLE && completado_txt14.getVisibility() != View.INVISIBLE) {
                } else {
                    det_detalle_click_opciones(target, parent, op_text);
                }
                break;
            case "7-4":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt5.getVisibility() != View.INVISIBLE && completado_txt6.getVisibility() != View.INVISIBLE
                        && completado_txt7.getVisibility() != View.INVISIBLE
                        && completado_txt9.getVisibility() != View.INVISIBLE && completado_txt10.getVisibility() != View.INVISIBLE
                        && completado_txt11.getVisibility() != View.INVISIBLE && completado_txt12.getVisibility() != View.INVISIBLE) {
                } else {
                    det_detalle_click_opciones(target, parent, op_text);
                }
                break;
            case "7-6":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt5.getVisibility() != View.INVISIBLE && completado_txt6.getVisibility() != View.INVISIBLE
                        && completado_txt7.getVisibility() != View.INVISIBLE
                        && completado_txt9.getVisibility() != View.INVISIBLE && completado_txt10.getVisibility() != View.INVISIBLE
                        && completado_txt11.getVisibility() != View.INVISIBLE && completado_txt12.getVisibility() != View.INVISIBLE
                        && completado_txt13.getVisibility() != View.INVISIBLE && completado_txt14.getVisibility() != View.INVISIBLE) {
                } else {
                    det_detalle_click_opciones(target, parent, op_text);
                }
                break;
            case "8-4":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt5.getVisibility() != View.INVISIBLE && completado_txt6.getVisibility() != View.INVISIBLE
                        && completado_txt7.getVisibility() != View.INVISIBLE && completado_txt8.getVisibility() != View.INVISIBLE
                        && completado_txt9.getVisibility() != View.INVISIBLE && completado_txt10.getVisibility() != View.INVISIBLE
                        && completado_txt11.getVisibility() != View.INVISIBLE && completado_txt12.getVisibility() != View.INVISIBLE) {
                } else {
                    det_detalle_click_opciones(target, parent, op_text);
                }
                break;
            case "8-6":
                if (completado_txt1.getVisibility() != View.INVISIBLE && completado_txt2.getVisibility() != View.INVISIBLE
                        && completado_txt3.getVisibility() != View.INVISIBLE && completado_txt4.getVisibility() != View.INVISIBLE
                        && completado_txt5.getVisibility() != View.INVISIBLE && completado_txt6.getVisibility() != View.INVISIBLE
                        && completado_txt7.getVisibility() != View.INVISIBLE && completado_txt8.getVisibility() != View.INVISIBLE
                        && completado_txt9.getVisibility() != View.INVISIBLE && completado_txt10.getVisibility() != View.INVISIBLE
                        && completado_txt11.getVisibility() != View.INVISIBLE && completado_txt12.getVisibility() != View.INVISIBLE
                        && completado_txt13.getVisibility() != View.INVISIBLE && completado_txt14.getVisibility() != View.INVISIBLE) {
                } else {
                    det_detalle_click_opciones(target, parent, op_text);
                }
                break;
        }
    }

    private void limpiar_completado() {
        switch (numero_letras) {
            default:
                detalle_limpiar_completado(completado_txt1);
                detalle_limpiar_completado(completado_txt2);
                detalle_limpiar_completado(completado_txt3);
                detalle_limpiar_completado(completado_txt4);
                detalle_limpiar_completado(completado_txt5);
                detalle_limpiar_completado(completado_txt6);
                detalle_limpiar_completado(completado_txt7);
                detalle_limpiar_completado(completado_txt8);
                detalle_limpiar_completado(completado_txt9);
                detalle_limpiar_completado(completado_txt10);
                detalle_limpiar_completado(completado_txt11);
                detalle_limpiar_completado(completado_txt12);
                detalle_limpiar_completado(completado_txt13);
                detalle_limpiar_completado(completado_txt14);
                detalle_limpiar_completado(completado_txt15);
                break;
            case "4-4":
                detalle_limpiar_completado(completado_txt1);
                detalle_limpiar_completado(completado_txt2);
                detalle_limpiar_completado(completado_txt3);
                detalle_limpiar_completado(completado_txt4);
                detalle_limpiar_completado(completado_txt9);
                detalle_limpiar_completado(completado_txt10);
                detalle_limpiar_completado(completado_txt11);
                detalle_limpiar_completado(completado_txt12);
                break;
            case "4-5":
                detalle_limpiar_completado(completado_txt1);
                detalle_limpiar_completado(completado_txt2);
                detalle_limpiar_completado(completado_txt3);
                detalle_limpiar_completado(completado_txt4);
                detalle_limpiar_completado(completado_txt9);
                detalle_limpiar_completado(completado_txt10);
                detalle_limpiar_completado(completado_txt11);
                detalle_limpiar_completado(completado_txt12);
                detalle_limpiar_completado(completado_txt13);
                break;
            case "4-6":
                detalle_limpiar_completado(completado_txt1);
                detalle_limpiar_completado(completado_txt2);
                detalle_limpiar_completado(completado_txt3);
                detalle_limpiar_completado(completado_txt4);
                detalle_limpiar_completado(completado_txt9);
                detalle_limpiar_completado(completado_txt10);
                detalle_limpiar_completado(completado_txt11);
                detalle_limpiar_completado(completado_txt12);
                detalle_limpiar_completado(completado_txt13);
                detalle_limpiar_completado(completado_txt14);
                break;
            case "4-7":
                detalle_limpiar_completado(completado_txt1);
                detalle_limpiar_completado(completado_txt2);
                detalle_limpiar_completado(completado_txt3);
                detalle_limpiar_completado(completado_txt4);
                detalle_limpiar_completado(completado_txt9);
                detalle_limpiar_completado(completado_txt10);
                detalle_limpiar_completado(completado_txt11);
                detalle_limpiar_completado(completado_txt12);
                detalle_limpiar_completado(completado_txt13);
                detalle_limpiar_completado(completado_txt14);
                detalle_limpiar_completado(completado_txt15);
                break;
            case "5-4":
                detalle_limpiar_completado(completado_txt1);
                detalle_limpiar_completado(completado_txt2);
                detalle_limpiar_completado(completado_txt3);
                detalle_limpiar_completado(completado_txt4);
                detalle_limpiar_completado(completado_txt5);
                detalle_limpiar_completado(completado_txt9);
                detalle_limpiar_completado(completado_txt10);
                detalle_limpiar_completado(completado_txt11);
                detalle_limpiar_completado(completado_txt12);
                break;
            case "5-5":
                detalle_limpiar_completado(completado_txt1);
                detalle_limpiar_completado(completado_txt2);
                detalle_limpiar_completado(completado_txt3);
                detalle_limpiar_completado(completado_txt4);
                detalle_limpiar_completado(completado_txt5);
                detalle_limpiar_completado(completado_txt9);
                detalle_limpiar_completado(completado_txt10);
                detalle_limpiar_completado(completado_txt11);
                detalle_limpiar_completado(completado_txt12);
                detalle_limpiar_completado(completado_txt13);
                break;
            case "5-6":
                detalle_limpiar_completado(completado_txt1);
                detalle_limpiar_completado(completado_txt2);
                detalle_limpiar_completado(completado_txt3);
                detalle_limpiar_completado(completado_txt4);
                detalle_limpiar_completado(completado_txt5);
                detalle_limpiar_completado(completado_txt9);
                detalle_limpiar_completado(completado_txt10);
                detalle_limpiar_completado(completado_txt11);
                detalle_limpiar_completado(completado_txt12);
                detalle_limpiar_completado(completado_txt13);
                detalle_limpiar_completado(completado_txt14);
                break;
            case "5-7":
                detalle_limpiar_completado(completado_txt1);
                detalle_limpiar_completado(completado_txt2);
                detalle_limpiar_completado(completado_txt3);
                detalle_limpiar_completado(completado_txt4);
                detalle_limpiar_completado(completado_txt5);
                detalle_limpiar_completado(completado_txt9);
                detalle_limpiar_completado(completado_txt10);
                detalle_limpiar_completado(completado_txt11);
                detalle_limpiar_completado(completado_txt12);
                detalle_limpiar_completado(completado_txt13);
                detalle_limpiar_completado(completado_txt14);
                detalle_limpiar_completado(completado_txt15);
                break;
            case "6-3":
                detalle_limpiar_completado(completado_txt1);
                detalle_limpiar_completado(completado_txt2);
                detalle_limpiar_completado(completado_txt3);
                detalle_limpiar_completado(completado_txt4);
                detalle_limpiar_completado(completado_txt5);
                detalle_limpiar_completado(completado_txt6);
                detalle_limpiar_completado(completado_txt9);
                detalle_limpiar_completado(completado_txt10);
                detalle_limpiar_completado(completado_txt11);
                break;
            case "6-4":
                detalle_limpiar_completado(completado_txt1);
                detalle_limpiar_completado(completado_txt2);
                detalle_limpiar_completado(completado_txt3);
                detalle_limpiar_completado(completado_txt4);
                detalle_limpiar_completado(completado_txt5);
                detalle_limpiar_completado(completado_txt6);
                detalle_limpiar_completado(completado_txt9);
                detalle_limpiar_completado(completado_txt10);
                detalle_limpiar_completado(completado_txt11);
                detalle_limpiar_completado(completado_txt12);
                break;
            case "6-5":
                detalle_limpiar_completado(completado_txt1);
                detalle_limpiar_completado(completado_txt2);
                detalle_limpiar_completado(completado_txt3);
                detalle_limpiar_completado(completado_txt4);
                detalle_limpiar_completado(completado_txt5);
                detalle_limpiar_completado(completado_txt6);
                detalle_limpiar_completado(completado_txt9);
                detalle_limpiar_completado(completado_txt10);
                detalle_limpiar_completado(completado_txt11);
                detalle_limpiar_completado(completado_txt12);
                detalle_limpiar_completado(completado_txt13);
                break;
            case "6-6":
                detalle_limpiar_completado(completado_txt1);
                detalle_limpiar_completado(completado_txt2);
                detalle_limpiar_completado(completado_txt3);
                detalle_limpiar_completado(completado_txt4);
                detalle_limpiar_completado(completado_txt5);
                detalle_limpiar_completado(completado_txt6);
                detalle_limpiar_completado(completado_txt9);
                detalle_limpiar_completado(completado_txt10);
                detalle_limpiar_completado(completado_txt11);
                detalle_limpiar_completado(completado_txt12);
                detalle_limpiar_completado(completado_txt13);
                detalle_limpiar_completado(completado_txt14);
                break;
            case "7-4":
                detalle_limpiar_completado(completado_txt1);
                detalle_limpiar_completado(completado_txt2);
                detalle_limpiar_completado(completado_txt3);
                detalle_limpiar_completado(completado_txt4);
                detalle_limpiar_completado(completado_txt5);
                detalle_limpiar_completado(completado_txt6);
                detalle_limpiar_completado(completado_txt7);
                detalle_limpiar_completado(completado_txt9);
                detalle_limpiar_completado(completado_txt10);
                detalle_limpiar_completado(completado_txt11);
                detalle_limpiar_completado(completado_txt12);
                break;
            case "7-6":
                detalle_limpiar_completado(completado_txt1);
                detalle_limpiar_completado(completado_txt2);
                detalle_limpiar_completado(completado_txt3);
                detalle_limpiar_completado(completado_txt4);
                detalle_limpiar_completado(completado_txt5);
                detalle_limpiar_completado(completado_txt6);
                detalle_limpiar_completado(completado_txt7);
                detalle_limpiar_completado(completado_txt9);
                detalle_limpiar_completado(completado_txt10);
                detalle_limpiar_completado(completado_txt11);
                detalle_limpiar_completado(completado_txt12);
                detalle_limpiar_completado(completado_txt13);
                detalle_limpiar_completado(completado_txt14);
                break;
            case "8-4":
                detalle_limpiar_completado(completado_txt1);
                detalle_limpiar_completado(completado_txt2);
                detalle_limpiar_completado(completado_txt3);
                detalle_limpiar_completado(completado_txt4);
                detalle_limpiar_completado(completado_txt5);
                detalle_limpiar_completado(completado_txt6);
                detalle_limpiar_completado(completado_txt7);
                detalle_limpiar_completado(completado_txt8);
                detalle_limpiar_completado(completado_txt9);
                detalle_limpiar_completado(completado_txt10);
                detalle_limpiar_completado(completado_txt11);
                detalle_limpiar_completado(completado_txt12);
                break;
            case "8-6":
                detalle_limpiar_completado(completado_txt1);
                detalle_limpiar_completado(completado_txt2);
                detalle_limpiar_completado(completado_txt3);
                detalle_limpiar_completado(completado_txt4);
                detalle_limpiar_completado(completado_txt5);
                detalle_limpiar_completado(completado_txt6);
                detalle_limpiar_completado(completado_txt7);
                detalle_limpiar_completado(completado_txt8);
                detalle_limpiar_completado(completado_txt9);
                detalle_limpiar_completado(completado_txt10);
                detalle_limpiar_completado(completado_txt11);
                detalle_limpiar_completado(completado_txt12);
                detalle_limpiar_completado(completado_txt13);
                detalle_limpiar_completado(completado_txt14);
                break;
        }
        cambiar_color("blanco");
    }

    private void herramienta_agregar_letra() {
        limpiar_completado();
        mostrar_ocultar_opciones_completo(1);
        herramienta_eliminar_presionado();
        char[] caracter = palabra_principal.toCharArray();
        detalle_add_letra_ocultar();
        switch (numero_letras) {
            case "2":
                switch (contador_agregar) {
                    case 1:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        break;
                    case 2:
                        contador_agregar=0;
                        btnAgregar.setBackgroundColor(Color.parseColor("#5A5F75"));
                        btnAgregar.setEnabled(false);
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        cambiar_nivel("ganar");
                        break;
                }
                break;
            case "3":
                switch (contador_agregar) {
                    case 1:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        break;
                    case 2:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        break;
                    case 3:
                        contador_agregar=0;
                        btnAgregar.setBackgroundColor(Color.parseColor("#5A5F75"));
                        btnAgregar.setEnabled(false);
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        cambiar_nivel("ganar");
                        break;
                }
                break;
            case "4":
                switch (contador_agregar) {
                    case 1:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        break;
                    case 2:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        break;
                    case 3:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        break;
                    case 4:
                        contador_agregar=0;
                        btnAgregar.setBackgroundColor(Color.parseColor("#5A5F75"));
                        btnAgregar.setEnabled(false);
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        cambiar_nivel("ganar");
                        break;
                }
                break;
            case "5":
                switch (contador_agregar) {
                    case 1:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        break;
                    case 2:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        break;
                    case 3:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        break;
                    case 4:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        break;
                    case 5:
                        contador_agregar=0;
                        btnAgregar.setBackgroundColor(Color.parseColor("#5A5F75"));
                        btnAgregar.setEnabled(false);
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        cambiar_nivel("ganar");
                        break;
                }
                break;
            case "6":
                switch (contador_agregar) {
                    case 1:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        break;
                    case 2:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        break;
                    case 3:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        break;
                    case 4:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        break;
                    case 5:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        break;
                    case 6:
                        contador_agregar=0;
                        btnAgregar.setBackgroundColor(Color.parseColor("#5A5F75"));
                        btnAgregar.setEnabled(false);
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[5]));
                        cambiar_nivel("ganar");
                        break;
                }
                break;
            case "7":
                switch (contador_agregar) {
                    case 1:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        break;
                    case 2:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        break;
                    case 3:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        break;
                    case 4:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        break;
                    case 5:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        break;
                    case 6:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        break;
                    case 7:
                        contador_agregar=0;
                        btnAgregar.setBackgroundColor(Color.parseColor("#5A5F75"));
                        btnAgregar.setEnabled(false);
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt7, String.valueOf(caracter[6]));
                        cambiar_nivel("ganar");
                        break;
                }
                break;
            case "8":
                switch (contador_agregar) {
                    case 1:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        break;
                    case 2:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        break;
                    case 3:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        break;
                    case 4:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        break;
                    case 5:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        break;
                    case 6:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        break;
                    case 7:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt7, String.valueOf(caracter[6]));
                        break;
                    case 8:
                        contador_agregar=0;
                        btnAgregar.setBackgroundColor(Color.parseColor("#5A5F75"));
                        btnAgregar.setEnabled(false);
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt7, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt8, String.valueOf(caracter[7]));
                        cambiar_nivel("ganar");
                        break;
                }
                break;
            case "4-4":
                switch (contador_agregar) {
                    case 1:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        break;
                    case 2:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        break;
                    case 3:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        break;
                    case 4:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        break;
                    case 5:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[4]));
                        break;
                    case 6:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[5]));
                        break;
                    case 7:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[6]));
                        break;
                    case 8:
                        contador_agregar=0;
                        btnAgregar.setBackgroundColor(Color.parseColor("#5A5F75"));
                        btnAgregar.setEnabled(false);
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt12, String.valueOf(caracter[7]));
                        cambiar_nivel("ganar");
                        break;
                }
                break;
            case "4-5":
                switch (contador_agregar) {
                    case 1:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        break;
                    case 2:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        break;
                    case 3:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        break;
                    case 4:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        break;
                    case 5:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[4]));
                        break;
                    case 6:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[5]));
                        break;
                    case 7:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[6]));
                        break;
                    case 8:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt12, String.valueOf(caracter[7]));
                        break;
                    case 9:
                        contador_agregar=0;
                        btnAgregar.setBackgroundColor(Color.parseColor("#5A5F75"));
                        btnAgregar.setEnabled(false);
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt12, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt13, String.valueOf(caracter[8]));
                        cambiar_nivel("ganar");
                        break;
                }
                break;
            case "4-6":
                switch (contador_agregar) {
                    case 1:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        break;
                    case 2:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        break;
                    case 3:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        break;
                    case 4:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        break;
                    case 5:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[4]));
                        break;
                    case 6:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[5]));
                        break;
                    case 7:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[6]));
                        break;
                    case 8:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt12, String.valueOf(caracter[7]));
                        break;
                    case 9:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt12, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt13, String.valueOf(caracter[8]));
                        break;
                    case 10:
                        contador_agregar=0;
                        btnAgregar.setBackgroundColor(Color.parseColor("#5A5F75"));
                        btnAgregar.setEnabled(false);
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt12, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt13, String.valueOf(caracter[8]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt14, String.valueOf(caracter[9]));
                        cambiar_nivel("ganar");
                        break;
                }
                break;
            case "4-7":
                switch (contador_agregar) {
                    case 1:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        break;
                    case 2:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        break;
                    case 3:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        break;
                    case 4:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        break;
                    case 5:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[4]));
                        break;
                    case 6:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[5]));
                        break;
                    case 7:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[6]));
                        break;
                    case 8:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt12, String.valueOf(caracter[7]));
                        break;
                    case 9:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt12, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt13, String.valueOf(caracter[8]));
                        break;
                    case 10:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt12, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt13, String.valueOf(caracter[8]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt14, String.valueOf(caracter[9]));
                        break;
                    case 11:
                        contador_agregar=0;
                        btnAgregar.setBackgroundColor(Color.parseColor("#5A5F75"));
                        btnAgregar.setEnabled(false);
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt12, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt13, String.valueOf(caracter[8]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt14, String.valueOf(caracter[9]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt15, String.valueOf(caracter[10]));
                        cambiar_nivel("ganar");
                        break;
                }
                break;
            case "5-4":
                switch (contador_agregar) {
                    case 1:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        break;
                    case 2:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        break;
                    case 3:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        break;
                    case 4:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        break;
                    case 5:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        break;
                    case 6:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[5]));
                        break;
                    case 7:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[6]));
                        break;
                    case 8:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[7]));
                        break;
                    case 9:
                        contador_agregar=0;
                        btnAgregar.setBackgroundColor(Color.parseColor("#5A5F75"));
                        btnAgregar.setEnabled(false);
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt12, String.valueOf(caracter[8]));
                        cambiar_nivel("ganar");
                        break;
                }
                break;
            case "5-5":
                switch (contador_agregar) {
                    case 1:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        break;
                    case 2:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        break;
                    case 3:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        break;
                    case 4:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        break;
                    case 5:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        break;
                    case 6:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[5]));
                        break;
                    case 7:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[6]));
                        break;
                    case 8:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[7]));
                        break;
                    case 9:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt12, String.valueOf(caracter[8]));
                        break;
                    case 10:
                        contador_agregar=0;
                        btnAgregar.setBackgroundColor(Color.parseColor("#5A5F75"));
                        btnAgregar.setEnabled(false);
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt12, String.valueOf(caracter[8]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt13, String.valueOf(caracter[9]));
                        cambiar_nivel("ganar");
                        break;
                }
                break;
            case "5-6":
                switch (contador_agregar) {
                    case 1:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        break;
                    case 2:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        break;
                    case 3:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        break;
                    case 4:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        break;
                    case 5:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        break;
                    case 6:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[5]));
                        break;
                    case 7:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[6]));
                        break;
                    case 8:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[7]));
                        break;
                    case 9:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt12, String.valueOf(caracter[8]));
                        break;
                    case 10:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt12, String.valueOf(caracter[8]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt13, String.valueOf(caracter[9]));
                        break;
                    case 11:
                        contador_agregar=0;
                        btnAgregar.setBackgroundColor(Color.parseColor("#5A5F75"));
                        btnAgregar.setEnabled(false);
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt12, String.valueOf(caracter[8]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt13, String.valueOf(caracter[9]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt14, String.valueOf(caracter[10]));
                        cambiar_nivel("ganar");
                        break;
                }
                break;
            case "5-7":
                switch (contador_agregar) {
                    case 1:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        break;
                    case 2:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        break;
                    case 3:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        break;
                    case 4:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        break;
                    case 5:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        break;
                    case 6:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[5]));
                        break;
                    case 7:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[6]));
                        break;
                    case 8:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[7]));
                        break;
                    case 9:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt12, String.valueOf(caracter[8]));
                        break;
                    case 10:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt12, String.valueOf(caracter[8]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt13, String.valueOf(caracter[9]));
                        break;
                    case 11:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt12, String.valueOf(caracter[8]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt13, String.valueOf(caracter[9]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt14, String.valueOf(caracter[10]));
                        break;
                    case 12:
                        contador_agregar=0;
                        btnAgregar.setBackgroundColor(Color.parseColor("#5A5F75"));
                        btnAgregar.setEnabled(false);
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt12, String.valueOf(caracter[8]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt13, String.valueOf(caracter[9]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt14, String.valueOf(caracter[10]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt15, String.valueOf(caracter[11]));
                        cambiar_nivel("ganar");
                        break;
                }
                break;
            case "6-3":
                switch (contador_agregar) {
                    case 1:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        break;
                    case 2:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        break;
                    case 3:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        break;
                    case 4:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        break;
                    case 5:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        break;
                    case 6:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        break;
                    case 7:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[6]));
                        break;
                    case 8:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[7]));
                        break;
                    case 9:
                        contador_agregar=0;
                        btnAgregar.setBackgroundColor(Color.parseColor("#5A5F75"));
                        btnAgregar.setEnabled(false);
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[8]));
                        cambiar_nivel("ganar");
                        break;
                }
                break;
            case "6-4":
                switch (contador_agregar) {
                    case 1:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        break;
                    case 2:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        break;
                    case 3:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        break;
                    case 4:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        break;
                    case 5:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        break;
                    case 6:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        break;
                    case 7:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[6]));
                        break;
                    case 8:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[7]));
                        break;
                    case 9:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[8]));
                        break;
                    case 10:
                        contador_agregar=0;
                        btnAgregar.setBackgroundColor(Color.parseColor("#5A5F75"));
                        btnAgregar.setEnabled(false);
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[8]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt12, String.valueOf(caracter[9]));
                        cambiar_nivel("ganar");
                        break;
                }
                break;
            case "6-5":
                switch (contador_agregar) {
                    case 1:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        break;
                    case 2:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        break;
                    case 3:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        break;
                    case 4:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        break;
                    case 5:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        break;
                    case 6:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        break;
                    case 7:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[6]));
                        break;
                    case 8:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[7]));
                        break;
                    case 9:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[8]));
                        break;
                    case 10:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[8]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt12, String.valueOf(caracter[9]));
                        break;
                    case 11:
                        contador_agregar=0;
                        btnAgregar.setBackgroundColor(Color.parseColor("#5A5F75"));
                        btnAgregar.setEnabled(false);
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[8]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt12, String.valueOf(caracter[9]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt13, String.valueOf(caracter[10]));
                        cambiar_nivel("ganar");
                        break;
                }
                break;
            case "6-6":
                switch (contador_agregar) {
                    case 1:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        break;
                    case 2:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        break;
                    case 3:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        break;
                    case 4:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        break;
                    case 5:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        break;
                    case 6:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        break;
                    case 7:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[6]));
                        break;
                    case 8:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[7]));
                        break;
                    case 9:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[8]));
                        break;
                    case 10:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[8]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt12, String.valueOf(caracter[9]));
                        break;
                    case 11:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[8]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt12, String.valueOf(caracter[9]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt13, String.valueOf(caracter[10]));
                        break;
                    case 12:
                        contador_agregar=0;
                        btnAgregar.setBackgroundColor(Color.parseColor("#5A5F75"));
                        btnAgregar.setEnabled(false);
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[8]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt12, String.valueOf(caracter[9]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt13, String.valueOf(caracter[10]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt14, String.valueOf(caracter[11]));
                        cambiar_nivel("ganar");
                        break;
                }
                break;
            case "7-4":
                switch (contador_agregar) {
                    case 1:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        break;
                    case 2:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        break;
                    case 3:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        break;
                    case 4:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        break;
                    case 5:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        break;
                    case 6:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        break;
                    case 7:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt7, String.valueOf(caracter[6]));
                        break;
                    case 8:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt7, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[7]));
                        break;
                    case 9:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt7, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[8]));
                        break;
                    case 10:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt7, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[8]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[9]));
                        break;
                    case 11:
                        contador_agregar=0;
                        btnAgregar.setBackgroundColor(Color.parseColor("#5A5F75"));
                        btnAgregar.setEnabled(false);
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt7, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[8]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[9]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt12, String.valueOf(caracter[10]));
                        cambiar_nivel("ganar");
                        break;
                }
                break;
            case "7-6":
                switch (contador_agregar) {
                    case 1:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        break;
                    case 2:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        break;
                    case 3:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        break;
                    case 4:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        break;
                    case 5:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        break;
                    case 6:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        break;
                    case 7:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt7, String.valueOf(caracter[6]));
                        break;
                    case 8:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt7, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[7]));
                        break;
                    case 9:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt7, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[8]));
                        break;
                    case 10:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt7, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[8]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[9]));
                        break;
                    case 11:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt7, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[8]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[9]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt12, String.valueOf(caracter[10]));
                        break;
                    case 12:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt7, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[8]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[9]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt12, String.valueOf(caracter[10]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt13, String.valueOf(caracter[11]));
                        break;
                    case 13:
                        contador_agregar=0;
                        btnAgregar.setBackgroundColor(Color.parseColor("#5A5F75"));
                        btnAgregar.setEnabled(false);
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt7, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[8]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[9]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt12, String.valueOf(caracter[10]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt13, String.valueOf(caracter[11]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt14, String.valueOf(caracter[12]));
                        cambiar_nivel("ganar");
                        break;
                }
                break;
            case "8-4":
                switch (contador_agregar) {
                    case 1:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        break;
                    case 2:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        break;
                    case 3:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        break;
                    case 4:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        break;
                    case 5:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        break;
                    case 6:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        break;
                    case 7:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt7, String.valueOf(caracter[6]));
                        break;
                    case 8:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt7, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt8, String.valueOf(caracter[7]));
                        break;
                    case 9:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt7, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt8, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[8]));
                        break;
                    case 10:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt7, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt8, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[8]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[9]));
                        break;
                    case 11:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt7, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt8, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[8]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[9]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[10]));
                        break;
                    case 12:
                        contador_agregar=0;
                        btnAgregar.setBackgroundColor(Color.parseColor("#5A5F75"));
                        btnAgregar.setEnabled(false);
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt7, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt8, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[8]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[9]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[10]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt12, String.valueOf(caracter[11]));
                        cambiar_nivel("ganar");
                        break;
                }
                break;
            case "8-6":
                switch (contador_agregar) {
                    case 1:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        break;
                    case 2:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        break;
                    case 3:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        break;
                    case 4:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        break;
                    case 5:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        break;
                    case 6:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        break;
                    case 7:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt7, String.valueOf(caracter[6]));
                        break;
                    case 8:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt7, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt8, String.valueOf(caracter[7]));
                        break;
                    case 9:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt7, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt8, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[8]));
                        break;
                    case 10:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt7, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt8, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[8]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[9]));
                        break;
                    case 11:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt7, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt8, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[8]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[9]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[10]));
                        break;
                    case 12:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt7, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt8, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[8]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[9]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[10]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt12, String.valueOf(caracter[11]));
                        break;
                    case 13:
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt7, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt8, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[8]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[9]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[10]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt12, String.valueOf(caracter[11]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt13, String.valueOf(caracter[12]));
                        break;
                    case 14:
                        contador_agregar=0;
                        btnAgregar.setBackgroundColor(Color.parseColor("#5A5F75"));
                        btnAgregar.setEnabled(false);
                        detalle_herramienta_agregar_MostrarLetra(completado_txt1, String.valueOf(caracter[0]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt2, String.valueOf(caracter[1]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt3, String.valueOf(caracter[2]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt4, String.valueOf(caracter[3]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt5, String.valueOf(caracter[4]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt6, String.valueOf(caracter[5]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt7, String.valueOf(caracter[6]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt8, String.valueOf(caracter[7]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt9, String.valueOf(caracter[8]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt10, String.valueOf(caracter[9]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt11, String.valueOf(caracter[10]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt12, String.valueOf(caracter[11]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt13, String.valueOf(caracter[12]));
                        detalle_herramienta_agregar_MostrarLetra(completado_txt14, String.valueOf(caracter[13]));
                        cambiar_nivel("ganar");
                        break;
                }
                break;
        }
    }

    //Prueba: ca-app-pub-3940256099942544/1033173712
    //Nivel 5: ca-app-pub-8291876581767416/7548747914
    private void add_inter() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this,"ca-app-pub-8291876581767416/7548747914", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                    }
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        mInterstitialAd = null;
                    }
                });
    }

    //Actualizado hasta el nivel 30
    private void cambio_imagen_nivel() {
        switch (actual_lvl) {
            case 1:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel1_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel1_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel1_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel1_d));
                break;
            case 2:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel2_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel2_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel2_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel2_d));
                break;
            case 3:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel3_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel3_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel3_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel3_d));
                break;
            case 4:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel4_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel4_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel4_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel4_d));
                break;
            case 5:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel5_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel5_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel5_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel5_d));
                break;
            case 6:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel6_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel6_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel6_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel6_d));
                break;
            case 7:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel7_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel7_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel7_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel7_d));
                break;
            case 8:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel8_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel8_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel8_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel8_d));
                break;
            case 9:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel9_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel9_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel9_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel9_d));
                break;
            case 10:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel10_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel10_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel10_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel10_d));
                break;
            case 11:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel11_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel11_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel11_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel11_d));
                break;
            case 12:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel12_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel12_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel12_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel12_d));
                break;
            case 13:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel13_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel13_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel13_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel13_d));
                break;
            case 14:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel14_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel14_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel14_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel14_d));
                break;
            case 15:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel15_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel15_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel15_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel15_d));
                break;
            case 16:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel16_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel16_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel16_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel16_d));
                break;
            case 17:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel17_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel17_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel17_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel17_d));
                break;
            case 18:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel18_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel18_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel18_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel18_d));
                break;
            case 19:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel19_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel19_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel19_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel19_d));
                break;
            case 20:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel20_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel20_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel20_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel20_d));
                break;
            case 21:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel21_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel21_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel21_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel21_d));
                break;
            case 22:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel22_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel22_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel22_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel22_d));
                break;
            case 23:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel23_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel23_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel23_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel23_d));
                break;
            case 24:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel24_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel24_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel24_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel24_d));
                break;
            case 25:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel25_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel25_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel25_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel25_d));
                break;
            case 26:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel26_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel26_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel26_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel26_d));
                break;
            case 27:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel27_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel27_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel27_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel27_d));
                break;
            case 28:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel28_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel28_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel28_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel28_d));
                break;
            case 29:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel29_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel29_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel29_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel29_d));
                break;
            case 30:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel30_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel30_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel30_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel30_d));
                break;
                /*
            case 31:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel31_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel31_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel31_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel31_d));
                break;
            case 32:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel32_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel32_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel32_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel32_d));
                break;
            case 33:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel33_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel33_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel33_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel33_d));
                break;
            case 34:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel34_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel34_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel34_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel34_d));
                break;
            case 35:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel35_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel35_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel35_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel35_d));
                break;
            case 36:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel36_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel36_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel36_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel36_d));
                break;
            case 37:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel37_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel37_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel37_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel37_d));
                break;
            case 38:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel38_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel38_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel38_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel38_d));
                break;
            case 39:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel39_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel39_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel39_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel39_d));
                break;
            case 40:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel40_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel40_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel40_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel40_d));
                break;
            case 41:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel41_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel41_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel41_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel41_d));
                break;
            case 42:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel42_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel42_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel42_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel42_d));
                break;
            case 43:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel43_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel43_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel43_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel43_d));
                break;
            case 44:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel44_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel44_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel44_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel44_d));
                break;
            case 45:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel45_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel45_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel45_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel45_d));
                break;
            case 46:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel46_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel46_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel46_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel46_d));
                break;
            case 47:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel47_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel47_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel47_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel47_d));
                break;
            case 48:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel48_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel48_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel48_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel48_d));
                break;
            case 49:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel49_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel49_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel49_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel49_d));
                break;
            case 50:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel50_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel50_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel50_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel50_d));
                break;
            case 51:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel51_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel51_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel51_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel51_d));
                break;
            case 52:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel52_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel52_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel52_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel52_d));
                break;
            case 53:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel53_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel53_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel53_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel53_d));
                break;
            case 54:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel54_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel54_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel54_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel54_d));
                break;
            case 55:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel55_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel55_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel55_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel55_d));
                break;
            case 56:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel56_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel56_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel56_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel56_d));
                break;
            case 57:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel57_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel57_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel57_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel57_d));
                break;
            case 58:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel58_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel58_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel58_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel58_d));
                break;
            case 59:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel59_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel59_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel59_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel59_d));
                break;
            case 60:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel60_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel60_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel60_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel60_d));
                break;
            */

            default:
                main_img1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel1_a));
                main_img2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel1_b));
                main_img3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel1_c));
                main_img4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lvlb_nivel1_d));
                SharedPreferences sp = this.getSharedPreferences("shared", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                int lvl_actualizado = 1;
                editor.putInt(getString(R.string.nivel_actual_2), lvl_actualizado);
                editor.apply();
                break;
        }
    }

    /* NIVEL A (nivel: 30) */

    // modif (ultimo_nivel + 1)
    private final int ultimo_nivel = 31;

    // - COMPLETO LVL 30 -
    private void palabra_a_buscar() {
        switch (actual_lvl) {
            case 1:
                palabra_principal = "Lifestealer";
                numero_letras = "4-7";
                break;
            case 2:
                palabra_principal = "Windranger";
                numero_letras = "4-6";
                break;
            case 3:
                palabra_principal = "Bounty Hunter";
                numero_letras = "6-6";
                break;
            case 4:
                palabra_principal = "Earth Spirit";
                numero_letras = "5-6";
                break;
            case 5:
                palabra_principal = "Gyrocopter";
                numero_letras = "4-6";
                break;
            case 6:
                palabra_principal = "Beastmaster";
                numero_letras = "5-6";
                break;
            case 7:
                palabra_principal = "Invoker";
                numero_letras = "7";
                break;
            case 8:
                palabra_principal = "Night Stalker";
                numero_letras = "5-7";
                break;
            case 9:
                palabra_principal = "Ogre Magi";
                numero_letras = "4-4";
                break;
            case 10:
                palabra_principal = "Leshrac";
                numero_letras = "7";
                break;
            case 11:
                palabra_principal = "Huskar";
                numero_letras = "6";
                break;
            case 12:
                palabra_principal = "Earthshaker";
                numero_letras = "5-6";
                break;
            case 13:
                palabra_principal = "Dazzle";
                numero_letras = "6";
                break;
            case 14:
                palabra_principal = "Morph ling";
                numero_letras = "5-4";
                break;
            case 15:
                palabra_principal = "Phantom Lancer";
                numero_letras = "7-6";
                break;
            case 16:
                palabra_principal = "Clockwerk";
                numero_letras = "5-4";
                break;
            case 17:
                palabra_principal = "Zeus";
                numero_letras = "4";
                break;
            case 18:
                palabra_principal = "Medusa";
                numero_letras = "6";
                break;
            case 19:
                palabra_principal = "Jakiro";
                numero_letras = "6";
                break;
            case 20:
                palabra_principal = "Bristleback";
                numero_letras = "7-4";
                break;
            case 21:
                palabra_principal = "Faceless Void";
                numero_letras = "8-4";
                break;
            case 22:
                palabra_principal = "Omniknight";
                numero_letras = "4-6";
                break;
            case 23:
                palabra_principal = "Razor";
                numero_letras = "5";
                break;
            case 24:
                palabra_principal = "Vengeful Spirit";
                numero_letras = "8-6";
                break;
            case 25:
                palabra_principal = "Elder Titan";
                numero_letras = "5-5";
                break;
            case 26:
                palabra_principal = "Bane";
                numero_letras = "4";
                break;
            case 27:
                palabra_principal = "Shadow Demon";
                numero_letras = "6-5";
                break;
            case 28:
                palabra_principal = "Warlock";
                numero_letras = "7";
                break;
            case 29:
                palabra_principal = "Sven";
                numero_letras = "4";
                break;
            case 30:
                palabra_principal = "Anti Mage";
                numero_letras = "4-4";
                break;
        }
        palabra_principal = palabra_principal.toUpperCase();
        palabra_principal = palabra_principal.replaceAll("[^A-Za-z0-9]+", "");

    }

    //cambiar Texto - COMPLETO LVL 60 -
    private void cambiar_letras_opciones() {
        switch (actual_lvl) {
            default:
            case 1: //Bounty Hunter
                opcion_txt1.setText("S");
                opcion_txt2.setText("O");
                opcion_txt3.setText("E");
                opcion_txt4.setText("I");
                opcion_txt5.setText("I");
                opcion_txt6.setText("L");
                opcion_txt7.setText("P");
                opcion_txt8.setText("F");
                opcion_txt9.setText("E");
                opcion_txt10.setText("L");
                opcion_txt11.setText("R");
                opcion_txt12.setText("T");
                opcion_txt13.setText("Q");
                opcion_txt14.setText("A");
                opcion_txt15.setText("E");
                opcion_txt16.setText("Y");
                break;
            case 2: //Doom
                opcion_txt1.setText("A");
                opcion_txt2.setText("N");
                opcion_txt3.setText("K");
                opcion_txt4.setText("R");
                opcion_txt5.setText("E");
                opcion_txt6.setText("S");
                opcion_txt7.setText("W");
                opcion_txt8.setText("T");
                opcion_txt9.setText("G");
                opcion_txt10.setText("R");
                opcion_txt11.setText("M");
                opcion_txt12.setText("I");
                opcion_txt13.setText("N");
                opcion_txt14.setText("O");
                opcion_txt15.setText("D");
                opcion_txt16.setText("E");
                break;
            case 3: //Pudge
                opcion_txt1.setText("T");
                opcion_txt2.setText("N");
                opcion_txt3.setText("B");
                opcion_txt4.setText("U");
                opcion_txt5.setText("R");
                opcion_txt6.setText("H");
                opcion_txt7.setText("L");
                opcion_txt8.setText("O");
                opcion_txt9.setText("E");
                opcion_txt10.setText("E");
                opcion_txt11.setText("N");
                opcion_txt12.setText("V");
                opcion_txt13.setText("S");
                opcion_txt14.setText("U");
                opcion_txt15.setText("T");
                opcion_txt16.setText("Y");
                break;
            case 4: //Sniper
                opcion_txt1.setText("I");
                opcion_txt2.setText("R");
                opcion_txt3.setText("B");
                opcion_txt4.setText("Y");
                opcion_txt5.setText("S");
                opcion_txt6.setText("E");
                opcion_txt7.setText("M");
                opcion_txt8.setText("T");
                opcion_txt9.setText("A");
                opcion_txt10.setText("R");
                opcion_txt11.setText("P");
                opcion_txt12.setText("O");
                opcion_txt13.setText("A");
                opcion_txt14.setText("I");
                opcion_txt15.setText("T");
                opcion_txt16.setText("H");
                break;
            case 5: //Tiny
                opcion_txt1.setText("C");
                opcion_txt2.setText("X");
                opcion_txt3.setText("G");
                opcion_txt4.setText("E");
                opcion_txt5.setText("O");
                opcion_txt6.setText("Z");
                opcion_txt7.setText("R");
                opcion_txt8.setText("Q");
                opcion_txt9.setText("A");
                opcion_txt10.setText("Y");
                opcion_txt11.setText("L");
                opcion_txt12.setText("T");
                opcion_txt13.setText("R");
                opcion_txt14.setText("O");
                opcion_txt15.setText("T");
                opcion_txt16.setText("P");
                break;
            case 6: //Kunkka
                opcion_txt1.setText("A");
                opcion_txt2.setText("X");
                opcion_txt3.setText("B");
                opcion_txt4.setText("T");
                opcion_txt5.setText("M");
                opcion_txt6.setText("N");
                opcion_txt7.setText("S");
                opcion_txt8.setText("H");
                opcion_txt9.setText("E");
                opcion_txt10.setText("A");
                opcion_txt11.setText("L");
                opcion_txt12.setText("E");
                opcion_txt13.setText("V");
                opcion_txt14.setText("S");
                opcion_txt15.setText("T");
                opcion_txt16.setText("R");
                break;
            case 7: //Faceless Void
                opcion_txt1.setText("E");
                opcion_txt2.setText("Y");
                opcion_txt3.setText("I");
                opcion_txt4.setText("S");
                opcion_txt5.setText("K");
                opcion_txt6.setText("A");
                opcion_txt7.setText("T");
                opcion_txt8.setText("V");
                opcion_txt9.setText("M");
                opcion_txt10.setText("P");
                opcion_txt11.setText("R");
                opcion_txt12.setText("Q");
                opcion_txt13.setText("N");
                opcion_txt14.setText("U");
                opcion_txt15.setText("B");
                opcion_txt16.setText("O");
                break;
            case 8: //Broodmother
                opcion_txt1.setText("G");
                opcion_txt2.setText("T");
                opcion_txt3.setText("N");
                opcion_txt4.setText("O");
                opcion_txt5.setText("L");
                opcion_txt6.setText("X");
                opcion_txt7.setText("S");
                opcion_txt8.setText("E");
                opcion_txt9.setText("H");
                opcion_txt10.setText("B");
                opcion_txt11.setText("A");
                opcion_txt12.setText("C");
                opcion_txt13.setText("I");
                opcion_txt14.setText("K");
                opcion_txt15.setText("R");
                opcion_txt16.setText("T");
                break;
            case 9: //Crystal Maiden
                opcion_txt1.setText("E");
                opcion_txt2.setText("Y");
                opcion_txt3.setText("O");
                opcion_txt4.setText("T");
                opcion_txt5.setText("A");
                opcion_txt6.setText("Q");
                opcion_txt7.setText("G");
                opcion_txt8.setText("P");
                opcion_txt9.setText("B");
                opcion_txt10.setText("G");
                opcion_txt11.setText("X");
                opcion_txt12.setText("I");
                opcion_txt13.setText("M");
                opcion_txt14.setText("L");
                opcion_txt15.setText("R");
                opcion_txt16.setText("S");
                break;
            case 10: //Juggernaut
                opcion_txt1.setText("A");
                opcion_txt2.setText("E");
                opcion_txt3.setText("I");
                opcion_txt4.setText("T");
                opcion_txt5.setText("X");
                opcion_txt6.setText("R");
                opcion_txt7.setText("N");
                opcion_txt8.setText("S");
                opcion_txt9.setText("K");
                opcion_txt10.setText("P");
                opcion_txt11.setText("C");
                opcion_txt12.setText("B");
                opcion_txt13.setText("O");
                opcion_txt14.setText("L");
                opcion_txt15.setText("M");
                opcion_txt16.setText("H");
                break;
            case 11: //Meepo
                opcion_txt1.setText("U");
                opcion_txt2.setText("Y");
                opcion_txt3.setText("A");
                opcion_txt4.setText("X");
                opcion_txt5.setText("M");
                opcion_txt6.setText("H");
                opcion_txt7.setText("X");
                opcion_txt8.setText("T");
                opcion_txt9.setText("C");
                opcion_txt10.setText("K");
                opcion_txt11.setText("E");
                opcion_txt12.setText("O");
                opcion_txt13.setText("S");
                opcion_txt14.setText("P");
                opcion_txt15.setText("R");
                opcion_txt16.setText("I");
                break;
            case 12: //Gyrocopter
                opcion_txt1.setText("T");
                opcion_txt2.setText("S");
                opcion_txt3.setText("R");
                opcion_txt4.setText("E");
                opcion_txt5.setText("P");
                opcion_txt6.setText("H");
                opcion_txt7.setText("R");
                opcion_txt8.setText("A");
                opcion_txt9.setText("Q");
                opcion_txt10.setText("X");
                opcion_txt11.setText("A");
                opcion_txt12.setText("E");
                opcion_txt13.setText("M");
                opcion_txt14.setText("H");
                opcion_txt15.setText("B");
                opcion_txt16.setText("K");
                break;
            case 13: //Lion
                opcion_txt1.setText("A");
                opcion_txt2.setText("S");
                opcion_txt3.setText("Z");
                opcion_txt4.setText("M");
                opcion_txt5.setText("R");
                opcion_txt6.setText("D");
                opcion_txt7.setText("T");
                opcion_txt8.setText("E");
                opcion_txt9.setText("Y");
                opcion_txt10.setText("O");
                opcion_txt11.setText("A");
                opcion_txt12.setText("L");
                opcion_txt13.setText("B");
                opcion_txt14.setText("U");
                opcion_txt15.setText("Z");
                opcion_txt16.setText("N");
                break;
            case 14: //Riki
                opcion_txt1.setText("R");
                opcion_txt2.setText("T");
                opcion_txt3.setText("M");
                opcion_txt4.setText("A");
                opcion_txt5.setText("I");
                opcion_txt6.setText("E");
                opcion_txt7.setText("H");
                opcion_txt8.setText("A");
                opcion_txt9.setText("G");
                opcion_txt10.setText("M");
                opcion_txt11.setText("L");
                opcion_txt12.setText("F");
                opcion_txt13.setText("O");
                opcion_txt14.setText("N");
                opcion_txt15.setText("S");
                opcion_txt16.setText("P");
                break;
            case 15: //Skywrath Mage
                opcion_txt1.setText("A");
                opcion_txt2.setText("N");
                opcion_txt3.setText("T");
                opcion_txt4.setText("S");
                opcion_txt5.setText("C");
                opcion_txt6.setText("P");
                opcion_txt7.setText("L");
                opcion_txt8.setText("R");
                opcion_txt9.setText("O");
                opcion_txt10.setText("Q");
                opcion_txt11.setText("A");
                opcion_txt12.setText("H");
                opcion_txt13.setText("B");
                opcion_txt14.setText("M");
                opcion_txt15.setText("N");
                opcion_txt16.setText("E");
                break;
            case 16: //Shadow Shaman
                opcion_txt1.setText("K");
                opcion_txt2.setText("Y");
                opcion_txt3.setText("P");
                opcion_txt4.setText("C");
                opcion_txt5.setText("S");
                opcion_txt6.setText("E");
                opcion_txt7.setText("A");
                opcion_txt8.setText("M");
                opcion_txt9.setText("O");
                opcion_txt10.setText("I");
                opcion_txt11.setText("R");
                opcion_txt12.setText("W");
                opcion_txt13.setText("L");
                opcion_txt14.setText("B");
                opcion_txt15.setText("K");
                opcion_txt16.setText("C");
                break;
            case 17: //Clinkz
                opcion_txt1.setText("B");
                opcion_txt2.setText("K");
                opcion_txt3.setText("E");
                opcion_txt4.setText("H");
                opcion_txt5.setText("N");
                opcion_txt6.setText("R");
                opcion_txt7.setText("S");
                opcion_txt8.setText("C");
                opcion_txt9.setText("U");
                opcion_txt10.setText("X");
                opcion_txt11.setText("A");
                opcion_txt12.setText("I");
                opcion_txt13.setText("Z");
                opcion_txt14.setText("V");
                opcion_txt15.setText("O");
                opcion_txt16.setText("S");
                break;
            case 18: //Undying
                opcion_txt1.setText("E");
                opcion_txt2.setText("P");
                opcion_txt3.setText("A");
                opcion_txt4.setText("R");
                opcion_txt5.setText("U");
                opcion_txt6.setText("B");
                opcion_txt7.setText("X");
                opcion_txt8.setText("M");
                opcion_txt9.setText("I");
                opcion_txt10.setText("B");
                opcion_txt11.setText("O");
                opcion_txt12.setText("S");
                opcion_txt13.setText("C");
                opcion_txt14.setText("D");
                opcion_txt15.setText("T");
                opcion_txt16.setText("Y");
                break;
            case 19: //Necrophos
                opcion_txt1.setText("P");
                opcion_txt2.setText("R");
                opcion_txt3.setText("J");
                opcion_txt4.setText("E");
                opcion_txt5.setText("R");
                opcion_txt6.setText("O");
                opcion_txt7.setText("K");
                opcion_txt8.setText("U");
                opcion_txt9.setText("S");
                opcion_txt10.setText("V");
                opcion_txt11.setText("I");
                opcion_txt12.setText("C");
                opcion_txt13.setText("A");
                opcion_txt14.setText("T");
                opcion_txt15.setText("B");
                opcion_txt16.setText("O");
                break;
            case 20: //Mirana
                opcion_txt1.setText("S");
                opcion_txt2.setText("A");
                opcion_txt3.setText("M");
                opcion_txt4.setText("B");
                opcion_txt5.setText("V");
                opcion_txt6.setText("E");
                opcion_txt7.setText("O");
                opcion_txt8.setText("I");
                opcion_txt9.setText("P");
                opcion_txt10.setText("C");
                opcion_txt11.setText("T");
                opcion_txt12.setText("Y");
                opcion_txt13.setText("B");
                opcion_txt14.setText("R");
                opcion_txt15.setText("K");
                opcion_txt16.setText("L");
                break;
            case 21:
                opcion_txt1.setText("A");
                opcion_txt2.setText("Y");
                opcion_txt3.setText("S");
                opcion_txt4.setText("T");
                opcion_txt5.setText("E");
                opcion_txt6.setText("O");
                opcion_txt7.setText("Q");
                opcion_txt8.setText("F");
                opcion_txt9.setText("S");
                opcion_txt10.setText("I");
                opcion_txt11.setText("L");
                opcion_txt12.setText("B");
                opcion_txt13.setText("C");
                opcion_txt14.setText("D");
                opcion_txt15.setText("V");
                opcion_txt16.setText("E");
                break;
            case 22:
                opcion_txt1.setText("K");
                opcion_txt2.setText("T");
                opcion_txt3.setText("I");
                opcion_txt4.setText("S");
                opcion_txt5.setText("O");
                opcion_txt6.setText("P");
                opcion_txt7.setText("I");
                opcion_txt8.setText("H");
                opcion_txt9.setText("B");
                opcion_txt10.setText("N");
                opcion_txt11.setText("R");
                opcion_txt12.setText("G");
                opcion_txt13.setText("A");
                opcion_txt14.setText("M");
                opcion_txt15.setText("T");
                opcion_txt16.setText("N");
                break;
            case 23:
                opcion_txt1.setText("A");
                opcion_txt2.setText("T");
                opcion_txt3.setText("Y");
                opcion_txt4.setText("B");
                opcion_txt5.setText("Z");
                opcion_txt6.setText("S");
                opcion_txt7.setText("I");
                opcion_txt8.setText("R");
                opcion_txt9.setText("E");
                opcion_txt10.setText("P");
                opcion_txt11.setText("M");
                opcion_txt12.setText("G");
                opcion_txt13.setText("O");
                opcion_txt14.setText("K");
                opcion_txt15.setText("N");
                opcion_txt16.setText("R");
                break;
            case 24:
                opcion_txt1.setText("V");
                opcion_txt2.setText("S");
                opcion_txt3.setText("E");
                opcion_txt4.setText("T");
                opcion_txt5.setText("N");
                opcion_txt6.setText("R");
                opcion_txt7.setText("U");
                opcion_txt8.setText("B");
                opcion_txt9.setText("I");
                opcion_txt10.setText("G");
                opcion_txt11.setText("A");
                opcion_txt12.setText("L");
                opcion_txt13.setText("F");
                opcion_txt14.setText("I");
                opcion_txt15.setText("E");
                opcion_txt16.setText("P");
                break;
            case 25:
                opcion_txt1.setText("D");
                opcion_txt2.setText("T");
                opcion_txt3.setText("E");
                opcion_txt4.setText("S");
                opcion_txt5.setText("P");
                opcion_txt6.setText("R");
                opcion_txt7.setText("O");
                opcion_txt8.setText("C");
                opcion_txt9.setText("T");
                opcion_txt10.setText("M");
                opcion_txt11.setText("A");
                opcion_txt12.setText("L");
                opcion_txt13.setText("B");
                opcion_txt14.setText("N");
                opcion_txt15.setText("E");
                opcion_txt16.setText("I");
                break;
            case 26:
                opcion_txt1.setText("N");
                opcion_txt2.setText("I");
                opcion_txt3.setText("T");
                opcion_txt4.setText("L");
                opcion_txt5.setText("P");
                opcion_txt6.setText("E");
                opcion_txt7.setText("H");
                opcion_txt8.setText("V");
                opcion_txt9.setText("M");
                opcion_txt10.setText("C");
                opcion_txt11.setText("Y");
                opcion_txt12.setText("A");
                opcion_txt13.setText("J");
                opcion_txt14.setText("U");
                opcion_txt15.setText("K");
                opcion_txt16.setText("B");
                break;
            case 27:
                opcion_txt1.setText("O");
                opcion_txt2.setText("D");
                opcion_txt3.setText("S");
                opcion_txt4.setText("P");
                opcion_txt5.setText("M");
                opcion_txt6.setText("U");
                opcion_txt7.setText("D");
                opcion_txt8.setText("I");
                opcion_txt9.setText("N");
                opcion_txt10.setText("E");
                opcion_txt11.setText("Q");
                opcion_txt12.setText("H");
                opcion_txt13.setText("O");
                opcion_txt14.setText("V");
                opcion_txt15.setText("W");
                opcion_txt16.setText("A");
                break;
            case 28:
                opcion_txt1.setText("R");
                opcion_txt2.setText("T");
                opcion_txt3.setText("W");
                opcion_txt4.setText("I");
                opcion_txt5.setText("C");
                opcion_txt6.setText("E");
                opcion_txt7.setText("P");
                opcion_txt8.setText("H");
                opcion_txt9.setText("L");
                opcion_txt10.setText("F");
                opcion_txt11.setText("B");
                opcion_txt12.setText("K");
                opcion_txt13.setText("B");
                opcion_txt14.setText("A");
                opcion_txt15.setText("V");
                opcion_txt16.setText("O");
                break;
            case 29:
                opcion_txt1.setText("V");
                opcion_txt2.setText("K");
                opcion_txt3.setText("A");
                opcion_txt4.setText("N");
                opcion_txt5.setText("O");
                opcion_txt6.setText("P");
                opcion_txt7.setText("B");
                opcion_txt8.setText("U");
                opcion_txt9.setText("X");
                opcion_txt10.setText("S");
                opcion_txt11.setText("L");
                opcion_txt12.setText("Y");
                opcion_txt13.setText("E");
                opcion_txt14.setText("Z");
                opcion_txt15.setText("J");
                opcion_txt16.setText("I");
                break;
            case 30:
                opcion_txt1.setText("N");
                opcion_txt2.setText("A");
                opcion_txt3.setText("R");
                opcion_txt4.setText("G");
                opcion_txt5.setText("C");
                opcion_txt6.setText("U");
                opcion_txt7.setText("A");
                opcion_txt8.setText("L");
                opcion_txt9.setText("O");
                opcion_txt10.setText("P");
                opcion_txt11.setText("I");
                opcion_txt12.setText("Q");
                opcion_txt13.setText("E");
                opcion_txt14.setText("M");
                opcion_txt15.setText("X");
                opcion_txt16.setText("T");
                break;
        }
    }



    /* DIALOG AGREGAR MONEDAS */
    private void dialog_agregar_monedas() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_monedas);

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

        btnText.setText(String.valueOf(gold));
        dialog.setOnCancelListener(dialogInterface -> { detalle_sonido("dialog"); dialog.dismiss();});
        root.setOnClickListener(view -> {detalle_sonido("dialog"); dialog.dismiss();});
        btnText.setOnClickListener( view -> {});
        btnSalir.setOnClickListener(view -> { detalle_sonido("dialog"); dialog.dismiss(); });

        share_twitter.setOnClickListener( view -> {detalle_sonido("dialog"); dialog_share("twitter", btnText, share_twitter); });
        share_facebook.setOnClickListener( view -> {detalle_sonido("dialog"); dialog_share("facebook", btnText, share_facebook);});
        share_whatsapp.setOnClickListener( view -> {detalle_sonido("dialog"); dialog_share("whatsapp", btnText, share_whatsapp);});
        reward_video.setOnClickListener( view -> { detalle_sonido("dialog"); show_ads(reward_video, btnText); });

        dialog_add_coins(dialog);
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
    //Codigo Produccion: ca-app-pub-8291876581767416/4922584578
    private void load_ads(ConstraintLayout layout_video) {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(this, "ca-app-pub-8291876581767416/4922584578",
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
                dialog_add_coins(50);
                int txtlvl = Integer.parseInt((String) btnMonedas.getText());
                txtlvl = txtlvl+50;
                btnMonedas.setText(String.valueOf(txtlvl));
                btnText.setText(String.valueOf(txtlvl));
                int rewardAmount = rewardItem.getAmount();
                String rewardType = rewardItem.getType();
                Toast.makeText(Nivel2.this, "¡50 monedas conseguidas!", Toast.LENGTH_SHORT).show();
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
                dialog_add_coins(20);
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
                dialog_add_coins(20);
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
                dialog_add_coins(20);
                editor.putInt(getString(R.string.whatsapp),1);
                editor.apply();
                layout_social.setEnabled(false);
                layout_social.setBackgroundResource(R.drawable.background_add_monedas_disable);
                break;
        }
        int txtlvl = Integer.parseInt((String) btnMonedas.getText());
        txtlvl = txtlvl+20;
        btnMonedas.setText(String.valueOf(txtlvl));
        btnText.setText(String.valueOf(txtlvl));
    }

    private void dialog_add_coins(Dialog dialog) {
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

    private void dialog_add_coins(int recompensa) {
        int recom_actualizado = gold + recompensa;
        SharedPreferences sp = this.getSharedPreferences("shared", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(getString(R.string.recompensa),recom_actualizado);
        editor.apply();
    }

    private void dialog_shared_preferences() {
        SharedPreferences sp = this.getSharedPreferences("redes", Context.MODE_PRIVATE);
        valor_twitter = sp.getInt(getString(R.string.twitter), 0);
        valor_facebook = sp.getInt(getString(R.string.facebook), 0);
        valor_whatsapp = sp.getInt(getString(R.string.whatsapp), 0);
    }

    
    /*SHARED PREFERENCE*/
    //Obtener el nivel actual, el nivel maximo al que se ha llegado y el total de oro
    private void sharedPreference() {
        SharedPreferences sp = this.getSharedPreferences("shared", Context.MODE_PRIVATE);
        //Sonido
        valor_volumen = sp.getInt(getString(R.string.estado_volumen), 1);
        //NivelA
        gold = sp.getInt(getString(R.string.recompensa), 500);
        actual_lvl = sp.getInt(getString(R.string.nivel_actual_2), 1);
        max_lvl = sp.getInt(getString(R.string.nivel_maximo_2), 1);
    }

    //Modicar el oro y el nivel al completarlo el actual
    private void modificarOroSegunNivel() {
        int lvl_actualizado = actual_lvl + 1;
        int recom_actualizado = gold + 5;
        int lvl_maximo;
        if (max_lvl == ultimo_nivel) { // max_lvl == 6
            lvl_maximo = ultimo_nivel;
            SharedPreferences sp = this.getSharedPreferences("shared", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt(getString(R.string.nivel_actual_2), lvl_actualizado);
            editor.putInt(getString(R.string.recompensa), gold);
            editor.putInt(getString(R.string.nivel_maximo_2), lvl_maximo);
            editor.apply();
        } else if (max_lvl < ultimo_nivel) {
            lvl_maximo = max_lvl + 1;
            SharedPreferences sp = this.getSharedPreferences("shared", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt(getString(R.string.nivel_actual_2), lvl_actualizado);
            editor.putInt(getString(R.string.recompensa), recom_actualizado);
            editor.putInt(getString(R.string.nivel_maximo_2), lvl_maximo);
            editor.apply();
        }
    }
    
}





//Al agregar una letra que ya estuvo agregada anteriormente, no se oculta la siguiente misma letra.
//Modificar AddLetraOcultar, ahi se puede corregir el error