package com.example.projetofinal;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.projetofinal.databinding.ActivityMapDadosCidadesBinding;

public class MapaDadosCidades extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapDadosCidadesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapDadosCidadesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng acegua = new LatLng(-31.867936810726288, -54.16436625932365);
        mMap.addMarker(new MarkerOptions().position(acegua).title("ACEGUA - 63"));

        LatLng aguaSanta = new LatLng(-28.176550407635638, -52.03495624765991);
        mMap.addMarker(new MarkerOptions().position(aguaSanta).title("AGUA SANTA - 73"));

        LatLng agudo = new LatLng(-29.642616080182652, -53.25255523997315);
        mMap.addMarker(new MarkerOptions().position(agudo).title("AGUDO - 146"));

        LatLng ajuricaba = new LatLng(-28.237546168878552, -53.770473111487924);
        mMap.addMarker(new MarkerOptions().position(ajuricaba).title("AJURICABA - 43"));

        LatLng alecrim = new LatLng(-27.65674635394726, -54.76532253318143);
        mMap.addMarker(new MarkerOptions().position(alecrim).title("ALECRIM - 61"));

        LatLng alegrete = new LatLng(-29.7900395819707, -55.795333086656115);
        mMap.addMarker(new MarkerOptions().position(alegrete).title("ALEGRETE - 1447"));

        LatLng alvorada = new LatLng(-29.999754505848532, -51.07597900793513);
        mMap.addMarker(new MarkerOptions().position(alvorada).title("ALVORADA - 4953"));

        LatLng canela = new LatLng(-29.364168007804267, -50.81018877111629);
        mMap.addMarker(new MarkerOptions().position(canela).title("CANELA - 958"));

        LatLng caxias = new LatLng(-28.938967272119104, -51.133958887569726);
        mMap.addMarker(new MarkerOptions().position(caxias).title("CAXIAS DO SUL - 10459"));

        LatLng ijui = new LatLng(-28.358097780170176, -53.91573621850154);
        mMap.addMarker(new MarkerOptions().position(ijui).title("IJUI - 1454"));

        LatLng santaRosa = new LatLng(-27.860970189298758, -54.47677560880229);
        mMap.addMarker(new MarkerOptions().position(santaRosa).title("SANTA ROSA - 1122"));

        LatLng campinaMissoes = new LatLng(-27.98943812133807, -54.840058300673824);
        mMap.addMarker(new MarkerOptions().position(campinaMissoes).title("CAMPINA DAS MISSOES - 24"));

        LatLng portoAlegre = new LatLng(-30.034742947202975, -51.22800931316008);
        mMap.addMarker(new MarkerOptions().position(portoAlegre).title("PORTO ALEGRE - 55453"));

        LatLng capaoCanoa = new LatLng(-29.763793022908587, -50.02937089204847);
        mMap.addMarker(new MarkerOptions().position(capaoCanoa).title("CAPAO DA CANOA - 1633"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(agudo,6));

        /*mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                //if(marker.equals(marker_1)){
                    Log.i("debug", "marker: " + marker.getTitle());
                    return true;
                //}
                //return false;
            }
        });*/
    }
}