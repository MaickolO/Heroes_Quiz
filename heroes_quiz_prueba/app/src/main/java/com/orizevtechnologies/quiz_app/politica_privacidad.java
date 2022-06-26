package com.orizevtechnologies.quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class politica_privacidad extends AppCompatActivity {

    LottieAnimationView back_politica;
    private TextView texto_politica;
    private int valor_volumen = 0;
    private final String nombre_app = "Dota 2: Heroes Quiz";
    private SoundPool sp;
    private int sound_abrir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politica_privacidad);
        Show_Hide_SystemUI();
        init_view();
        sonidos();
        retroceder_animacion();
        agregar_texto();
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
        back_politica.playAnimation();
        finish();
        overridePendingTransition( R.anim.stay_horizontal, R.anim.slide_left_out);
    }

    private void init_view() {
        back_politica = findViewById(R.id.back_politica);
        texto_politica = findViewById(R.id.texto_politica);
    }

    private void retroceder_animacion() {
        back_politica.setOnClickListener(view -> {
            detalle_sonido();
            back_politica.playAnimation();
            finish();
            overridePendingTransition( R.anim.stay_horizontal, R.anim.slide_left_out);
        });
    }

    private void detalle_sonido() {
        if (valor_volumen==1) {
            sp.play(sound_abrir, 1,1,1,0,1);
        }
    }

    private void sonidos() {
        SharedPreferences sp2 = this.getSharedPreferences("shared", Context.MODE_PRIVATE);
        valor_volumen = sp2.getInt(getString(R.string.estado_volumen), 1);
        sp = new SoundPool.Builder().setMaxStreams(10).build();
        sound_abrir = sp.load(this, R.raw.abrir,1);
    }

    private void agregar_texto() {
        String correo_electronico = "michaelorizev@gmail.com";
        String texto = "Al instalar nuestro aplicativo móvil \"" + nombre_app + "\" usted indica que conoce y entiende lo siguiente:\n" +
                "\n" +
                "1. A través de esta aplicación no se recaban datos de carácter personal de los usuarios.\n" +
                "\n" +
                "2. No se registran direcciones IP.\n" +
                "\n" +
                "3. No se accede a las cuentas de correo de los usuarios.\n" +
                "\n" +
                "4. La aplicación no guarda datos ni hace seguimientos sobre tiempos y horarios de utilización.\n" +
                "\n" +
                "5. La aplicación no guarda información relativa a tu dispositivo como, por ejemplo, fallos, actividad del sistema, ajustes del hardware, tipo de navegador, idioma del navegador.\n" +
                "\n" +
                "6. La aplicación no accede a tus contactos ni agendas.\n" +
                "\n" +
                "7. La aplicación no recopila información sobre tu ubicación real.\n" +
                "\n" +
                "8. Remarketing con Google AdMob\n" +
                "Proveedores como Google, utilizan cookies de primer nivel y cookies de terceros u otros identificadores de terceros para compilar datos sobre las interacciones de los usuarios con Las impresiones de anuncios y otras funciones de servicio de anuncios.\n" +
                "\n" +
                "9. Clasificación por edades: PEGI 3 - Apto para todos los públicos.\n" +
                "\n" +
                "10. Cargos y cuotas:\n" +
                "Cualquier uso de esta aplicación es totalmente gratuito.\n" +
                "\n" +
                "11. Cambios en nuestra Política de Privacidad:\n" +
                "Nuestra Política de Privacidad puede cambiar regularmente.\n" +
                "Publicaremos cualquier cambio de política de privacidad en esta sección, por lo que debe revisarla periódicamente.\n" +
                "\n" +
                "12. Contacto:\n" +
                "Si tiene alguna pregunta sobre esta Política o para informar de cualquier violación de la Política, envíe un correo electrónico a: " + correo_electronico;
        texto_politica.setText(texto);
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
}

