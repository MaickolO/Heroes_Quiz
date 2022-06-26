package com.orizevtechnologies.quiz_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class configuracion extends AppCompatActivity {

    //Recursos
    private LottieAnimationView cerrar_animacion;
    private LottieAnimationView compartir;
    private LottieAnimationView politica;
    private LottieAnimationView contacto;
    private SwitchCompat switch_sonido;
    //Vistas
    private ConstraintLayout layout_sonido;
    private ConstraintLayout layout_compartir;
    private ConstraintLayout layout_politica;
    private ConstraintLayout layout_contacto;
    //Control Volumen
    private int valor_volumen = 0;
    private int nuevo_volumen = 0;
    private SoundPool sp;
    private int sound_abrir;
    //Compartir
    private final String nombre_app = "Dota 2: Heroes Quiz";
    private final String link = "https://play.google.com/store/apps/details?id=com.orizevtechnologies.quiz_app";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        Show_Hide_SystemUI();
        init_view();
        lottie();
        sonidos();
        cambiar_switch();
        cambio_sonido();
        click_layouts();
    }

    @Override //Retroceder mediante boton
    public void onBackPressed() {
        detalle_sonido();
        finish();
        overridePendingTransition( R.anim.stay_vertical, R.anim.slide_down );
    }

    //Inicializar Variables
    private void init_view() {
        cerrar_animacion = findViewById(R.id.close_lottie);
        compartir = findViewById(R.id.lottie_compartir);
        politica = findViewById(R.id.lottie_politica);
        contacto = findViewById(R.id.lottie_contacto);
        switch_sonido = findViewById(R.id.switch_sonido);
        layout_sonido = findViewById(R.id.layout_sonido);
        layout_compartir = findViewById(R.id.layout_compartir);
        layout_politica = findViewById(R.id.layout_politica);
        layout_contacto = findViewById(R.id.layout_contacto);
    }

    //Cambiar Controles
    @SuppressLint("IntentReset")
    private void click_layouts() {
        //Cambiar sonido
        SharedPreferences sp2 = configuracion.this.getSharedPreferences("shared", Context.MODE_PRIVATE);
        layout_sonido.setOnClickListener(view -> {
            if (switch_sonido.isChecked()) {
                nuevo_volumen = 1;
                SharedPreferences.Editor editor = sp2.edit();
                editor.putInt(getString(R.string.estado_volumen), nuevo_volumen);
                editor.apply();
                valor_volumen = sp2.getInt(getString(R.string.estado_volumen), 1);
                switch_sonido.setChecked(false);
            } else if (!switch_sonido.isChecked()) {
                nuevo_volumen = 2;
                SharedPreferences.Editor editor = sp2.edit();
                editor.putInt(getString(R.string.estado_volumen), nuevo_volumen);
                editor.apply();
                valor_volumen = sp2.getInt(getString(R.string.estado_volumen), 1);
                switch_sonido.setChecked(true);
                sp.play(sound_abrir, 1,1,1,0,1);
            }
        });
        //Función compartir
        layout_compartir.setOnClickListener(view -> {
            detalle_sonido();
            compartir.playAnimation();
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Mira este nuevo juego \""+nombre_app+"\" en Google Play! juégalo GRATIS Ahora - "+link);
            sendIntent.setType("text/plain");
            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        });
        //Función Contacto
        layout_contacto.setOnClickListener(view -> {
            detalle_sonido();
            contacto.playAnimation();
            enviarEmail();
        });
        //Función Política
        layout_politica.setOnClickListener(view -> {
            detalle_sonido();
            politica.playAnimation();
            Intent intent = new Intent(configuracion.this, politica_privacidad.class);
            startActivity(intent);
            overridePendingTransition( R.anim.slide_in_right, R.anim.stay_vertical );
        });
    }

    //Click Animaciones
    private void lottie() {
        cerrar_animacion.setOnClickListener(view -> {
            detalle_sonido();
            cerrar_animacion.playAnimation();
            finish();
            overridePendingTransition( R.anim.stay_vertical, R.anim.slide_down );
        });

        compartir.setOnClickListener(view -> {
            detalle_sonido();
            compartir.playAnimation();
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Mira este nuevo juego \""+nombre_app+"\" en Google Play! juégalo GRATIS Ahora - "+link);
            sendIntent.setType("text/plain");
            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        });

        politica.setOnClickListener(view -> {
            detalle_sonido();
            politica.playAnimation();
            Intent intent = new Intent(this, politica_privacidad.class);
            startActivity(intent);
            overridePendingTransition( R.anim.slide_in_right, R.anim.stay_vertical );
        });

        contacto.setOnClickListener(view -> {
            detalle_sonido();
            contacto.playAnimation();
            enviarEmail();
        });
    }

    //Shared Preference (Sonido)
    private void cambio_sonido() {
        switch_sonido.setOnCheckedChangeListener((compoundButton, b) -> {
            SharedPreferences sp2 = configuracion.this.getSharedPreferences("shared", Context.MODE_PRIVATE);
            valor_volumen = sp2.getInt(getString(R.string.estado_volumen), 1);
            if (b) {
                nuevo_volumen = 1;
                SharedPreferences.Editor editor = sp2.edit();
                editor.putInt(getString(R.string.estado_volumen), nuevo_volumen);
                editor.apply();
                sp.play(sound_abrir, 1,1,1,0,1);
            } else {
                nuevo_volumen = 2;
                SharedPreferences.Editor editor = sp2.edit();
                editor.putInt(getString(R.string.estado_volumen), nuevo_volumen);
                editor.apply();
            }
            valor_volumen = sp2.getInt(getString(R.string.estado_volumen), 1);
        });
    }

    //Cargar Sonido
    private void sonidos() {
        sp = new SoundPool.Builder().setMaxStreams(10).build();
        sound_abrir = sp.load(this, R.raw.abrir,1);
    }

    //Reproducir Sonido
    private void detalle_sonido() {
        if (valor_volumen==1) {
            sp.play(sound_abrir, 1,1,1,0,1);
        }
    }

    //Modificar Switch segun el valor del sonido guardado
    private void cambiar_switch() {
        SharedPreferences sp2 = configuracion.this.getSharedPreferences("shared", Context.MODE_PRIVATE);
        valor_volumen = sp2.getInt(getString(R.string.estado_volumen), 1);
        if (valor_volumen == 1) {
            switch_sonido.setChecked(true);
        } else if (valor_volumen == 2){
            switch_sonido.setChecked(false);
        }
    }

    //Funcion Email
    public void  enviarEmail(){
        String[] TO = {"michaelorizev@gmail.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, nombre_app+" - contacto");
        try {
            startActivity(emailIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(configuracion.this, "No tienes ningún gestor de correo instalado", Toast.LENGTH_SHORT).show();
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

}