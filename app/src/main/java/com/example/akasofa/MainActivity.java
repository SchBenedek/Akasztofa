package com.example.akasofa;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView betuk;
    private TextView kitalalando;
    private Button minusz;
    private Button plusz;
    private Button tipp;
    private ImageView kep;
    private Integer szam=0;
    private Integer elet=13;
    private int[] kepek={R.drawable.akasztofa00, R.drawable.akasztofa01, R.drawable.akasztofa02, R.drawable.akasztofa03, R.drawable.akasztofa04, R.drawable.akasztofa05, R.drawable.akasztofa06, R.drawable.akasztofa07, R.drawable.akasztofa08, R.drawable.akasztofa09, R.drawable.akasztofa10, R.drawable.akasztofa11, R.drawable.akasztofa12, };
    Random random=new Random();
    private Integer randomSzam=0;
    private String randomSzo;
    private String vonal="";
    List<String> vonalakLista=new ArrayList<String>();
    Map<Integer, String> SzoBetuk=new HashMap<>();

    String[] szavak={
            "peugeot",
            "citroen",
            "fiat",
            "opel",
            "hellonestjs",
            "jarmu",
            "sajtok",
            "memoriak",
            "alma",
            "banan"
    };
    String[] betukLista={"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "r", "s", "t", "u", "v", "w", "x", "y", "z"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();

        plusz.setOnClickListener(v ->{
            if(szam==betukLista.length-1){
                szam=0;
                Megjelenit();
            }
            else{
                szam++;
                Megjelenit();
            }
        });
        minusz.setOnClickListener(v ->{
            if(szam==0){
                szam=betukLista.length-1;
                Megjelenit();
            }
            else{
                szam--;
                Megjelenit();
            }
        });
        tipp.setOnClickListener(v->{
            Jatek();
        });
    }

    private void Megjelenit(){
        betuk.setText(betukLista[szam]);
    }

    private void VonalMegjelenit(){
        vonalakLista.clear();
        vonal = "";
        for(int i=0; i<Szo().length(); i++)
        {
            vonalakLista.add("_ ");
            vonal+="_ ";
        }
        kitalalando.setText(vonal);
    }

    private void SzoBetukFeltolt(){
        SzoBetuk.clear();
        for(int i=0; i<Szo().length(); i++)
        {
            String b=String.valueOf(randomSzo.charAt(i));
            SzoBetuk.put(i, b);
        }
    }

    private String Szo(){
        randomSzo=szavak[randomSzam];
        return randomSzo;
    }

    private void VonalValtozas(){
        String valtozas=String.join("", vonalakLista);
        kitalalando.setText(valtozas);
    }

    private void Ujjatek(){
        VonalMegjelenit();
        SzoBetukFeltolt();
        elet=13;
        kep.setImageResource(R.drawable.akasztofa00);
    }

    private void Jatek(){
        Boolean igazE=false;
        for(int i=0; i<Szo().length();i++){
            if(betuk.getText().toString().equals(SzoBetuk.get(i))){
                vonalakLista.set(i, SzoBetuk.get(i));
                VonalValtozas();
                igazE=true;
                if(!vonalakLista.contains("_ ")){
                    JatekVege();
                }
            }
        }
        if(!igazE){
            elet--;
            if(elet>=1)
            kep.setImageResource(kepek[12-elet]);
            else{
                kep.setImageResource(R.drawable.akasztofa13);
                JatekVege();
            }
        }
    }

    public void JatekVege(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Szeretne új játékot kezdeni?");
        if(elet==0){
            builder.setTitle("Vereség");
        }
        else{
            builder.setTitle("Győzelem");
        }
        builder.setPositiveButton("Nem", (DialogInterface.OnClickListener) (dialog, which) -> {
            // When the user click yes button then app will close
            finish();
        });
        builder.setNegativeButton("Igen", (DialogInterface.OnClickListener) (dialog, which) -> {
            // If user click no then dialog box is canceled.
            Ujjatek();
            dialog.cancel();
        });
        builder.show();
    }





    private void init(){
        betuk=findViewById(R.id.betuk);
        minusz=findViewById(R.id.minusz);
        plusz=findViewById(R.id.plusz);
        tipp=findViewById(R.id.tipp);
        kep=findViewById(R.id.kep);
        kitalalando=findViewById(R.id.kitalalando);
        randomSzam=random.nextInt(10);
        VonalMegjelenit();
    }
}