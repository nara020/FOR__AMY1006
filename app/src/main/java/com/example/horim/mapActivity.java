package com.example.horim;
import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import noman.googleplaces.NRPlaces;
import noman.googleplaces.Place;
import noman.googleplaces.PlaceType;
import noman.googleplaces.PlacesException;
import noman.googleplaces.PlacesListener;

public class mapActivity extends AppCompatActivity
        implements OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback,  PlacesListener{



    private GoogleMap mGoogleMap = null;
    private Marker currentMarker = null;

    private static final String TAG = "googlemap_example";
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int UPDATE_INTERVAL_MS = 1000;  // 1초
    private static final int FASTEST_UPDATE_INTERVAL_MS = 500; // 0.5초


    // onRequestPermissionsResult에서 수신된 결과에서 ActivityCompat.requestPermissions를 사용한 퍼미션 요청을 구별하기 위해 사용됩니다.
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    boolean needRequest = false;


    // 앱을 실행하기 위해 필요한 퍼미션을 정의합니다.
    String[] REQUIRED_PERMISSIONS  = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};  // 외부 저장소


    Location mCurrentLocatiion;
    LatLng currentPosition;


    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest locationRequest;
    private Location location;


    private View mLayout;  // Snackbar 사용하기 위해서는 View가 필요합니다.
    // (참고로 Toast에서는 Context가 필요했습니다.)

    List<Marker> previous_marker = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_map);

        previous_marker = new ArrayList<Marker>();




        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                        showPlaceInformation(currentPosition);


            }
        });

        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mGoogleMap.clear();
                if (previous_marker != null)
                    previous_marker.clear();//지역정보 마커 클리어

                ShowServiceRestorant_NightFood();
                ShowServiceRestorant_Itary();
                ShowServiceRestorant_Japan();
                ShowServiceRestorant_China();
                ShowServiceRestorant_Chicken();
                ShowServiceRestorant_FastFood();
                ShowServiceRestorant_Korea();

            }
        });

        Button korea_food1 =  (Button)findViewById(R.id.korea_food1);
        korea_food1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mGoogleMap.clear();//지도 클리어



                if (previous_marker != null)
                    previous_marker.clear();//지역정보 마커 클리어

                ShowServiceRestorant_Korea();


            }
        });

        Button china_food1 =  (Button)findViewById(R.id.china_food1);
        china_food1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoogleMap.clear();//지도 클리어



                if (previous_marker != null)
                    previous_marker.clear();//지역정보 마커 클리어

                ShowServiceRestorant_China();


            }
        });

        Button japan_food1 =  (Button)findViewById(R.id.japan_food1);
        japan_food1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoogleMap.clear();//지도 클리어



                if (previous_marker != null)
                    previous_marker.clear();//지역정보 마커 클리어

                ShowServiceRestorant_Japan();



            }
        });

        Button italy_food1 =  (Button)findViewById(R.id.italy_food1);
        italy_food1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoogleMap.clear();//지도 클리어



                if (previous_marker != null)
                    previous_marker.clear();//지역정보 마커 클리어

                ShowServiceRestorant_Itary();


            }
        });

        Button fast_food1 =  (Button)findViewById(R.id.fast_food1);
        fast_food1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoogleMap.clear();//지도 클리어



                if (previous_marker != null)
                    previous_marker.clear();//지역정보 마커 클리어

                ShowServiceRestorant_FastFood();


            }
        });

        Button night_food1 =  (Button)findViewById(R.id.night_food1);
        night_food1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoogleMap.clear();//지도 클리어



                if (previous_marker != null)
                    previous_marker.clear();//지역정보 마커 클리어

                ShowServiceRestorant_NightFood();


            }
        });


        mLayout = findViewById(R.id.layout_map);


        Log.d(TAG, "onCreate");



        locationRequest = new LocationRequest()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
               // .setInterval(UPDATE_INTERVAL_MS)
            //    .setFastestInterval(FASTEST_UPDATE_INTERVAL_MS);


        LocationSettingsRequest.Builder builder =
                new LocationSettingsRequest.Builder();

        builder.addLocationRequest(locationRequest);


        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }




    LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);

            List<Location> locationList = locationResult.getLocations();

            if (locationList.size() > 0) {
                location = locationList.get(locationList.size() - 1);
                //location = locationList.get(0);

                currentPosition
                        = new LatLng(location.getLatitude(), location.getLongitude());


                String markerTitle = getCurrentAddress(currentPosition);
                String markerSnippet = "위도:" + String.valueOf(location.getLatitude())
                        + " 경도:" + String.valueOf(location.getLongitude());

                Log.d(TAG, "onLocationResult : " + markerSnippet);


                //현재 위치에 마커 생성하고 이동
                setCurrentLocation(location, markerTitle, markerSnippet);

                mCurrentLocatiion = location;
            }


        }

    };



    private void startLocationUpdates() {

        if (!checkLocationServicesStatus()) {

            Log.d(TAG, "startLocationUpdates : call showDialogForLocationServiceSetting");
            showDialogForLocationServiceSetting();
        }else {

            int hasFineLocationPermission = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION);
            int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION);



            if (hasFineLocationPermission != PackageManager.PERMISSION_GRANTED ||
                    hasCoarseLocationPermission != PackageManager.PERMISSION_GRANTED   ) {

                Log.d(TAG, "startLocationUpdates : 퍼미션 안가지고 있음");
                return;
            }


            Log.d(TAG, "startLocationUpdates : call mFusedLocationClient.requestLocationUpdates");

            mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());

            if (checkPermission())
                mGoogleMap.setMyLocationEnabled(true);

        }

    }



    @Override
    public void onMapReady(GoogleMap googleMap) {

        Log.d(TAG, "onMapReady :");

        mGoogleMap = googleMap;


        //런타임 퍼미션 요청 대화상자나 GPS 활성 요청 대화상자 보이기전에
        //지도의 초기위치를 서울로 이동
        setDefaultLocation();



        //런타임 퍼미션 처리
        // 1. 위치 퍼미션을 가지고 있는지 체크합니다.
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);



        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED   ) {

            // 2. 이미 퍼미션을 가지고 있다면
            // ( 안드로이드 6.0 이하 버전은 런타임 퍼미션이 필요없기 때문에 이미 허용된 걸로 인식합니다.)


            startLocationUpdates(); // 3. 위치 업데이트 시작


        }else {  //2. 퍼미션 요청을 허용한 적이 없다면 퍼미션 요청이 필요합니다. 2가지 경우(3-1, 4-1)가 있습니다.

            // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우에는
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])) {

                // 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있습니다.
                Snackbar.make(mLayout, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.",
                        Snackbar.LENGTH_INDEFINITE).setAction("확인", new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        // 3-3. 사용자게에 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                        ActivityCompat.requestPermissions( mapActivity.this, REQUIRED_PERMISSIONS,
                                PERMISSIONS_REQUEST_CODE);
                    }
                }).show();


            } else {
                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 합니다.
                // 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions( this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }

        }



        mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {

                Log.d( TAG, "onMapClick :");
            }
        });



    }




    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "onStart");

        if (checkPermission()) {

            Log.d(TAG, "onStart : call mFusedLocationClient.requestLocationUpdates");
            mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);

            if (mGoogleMap!=null)
                mGoogleMap.setMyLocationEnabled(true);

        }


    }


    @Override
    protected void onStop() {

        super.onStop();

        if (mFusedLocationClient != null) {

            Log.d(TAG, "onStop : call stopLocationUpdates");
            mFusedLocationClient.removeLocationUpdates(locationCallback);
        }
    }




    public String getCurrentAddress(LatLng latlng) {

        //지오코더... GPS를 주소로 변환
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        List<Address> addresses;

        try {

            addresses = geocoder.getFromLocation(
                    latlng.latitude,
                    latlng.longitude,
                    1);
        } catch (IOException ioException) {
            //네트워크 문제
            Toast.makeText(this, "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show();
            return "지오코더 서비스 사용불가";
        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(this, "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();
            return "잘못된 GPS 좌표";

        }


        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(this, "주소 미발견", Toast.LENGTH_LONG).show();
            return "주소 미발견";

        } else {
            Address address = addresses.get(0);
            return address.getAddressLine(0).toString();
        }

    }


    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }


    public void setCurrentLocation(Location location, String markerTitle, String markerSnippet) {


        if (currentMarker != null) currentMarker.remove();


        LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(currentLatLng);
        markerOptions.title(markerTitle);
        markerOptions.snippet(markerSnippet);
        markerOptions.draggable(true);


        currentMarker = mGoogleMap.addMarker(markerOptions);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(currentLatLng);
        mGoogleMap.moveCamera(cameraUpdate);

    }


    public void setDefaultLocation() {


        //디폴트 위치, Suwon University
        LatLng DEFAULT_LOCATION = new LatLng(37.209023, 126.976318);
        String markerTitle = "위치정보 가져올 수 없음";
        String markerSnippet = "위치 퍼미션과 GPS 활성 요부 확인하세요";


        if (currentMarker != null) currentMarker.remove();

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(DEFAULT_LOCATION);
        markerOptions.title(markerTitle);
        markerOptions.snippet(markerSnippet);
        markerOptions.draggable(true);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));


        ShowServiceRestorant_Korea();

        currentMarker = mGoogleMap.addMarker(markerOptions);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(DEFAULT_LOCATION, 15);
        mGoogleMap.moveCamera(cameraUpdate);

    }


    //여기부터는 런타임 퍼미션 처리을 위한 메소드들
    private boolean checkPermission() {

        int hasFineLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);



        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED   ) {
            return true;
        }

        return false;

    }



    /*
     * ActivityCompat.requestPermissions를 사용한 퍼미션 요청의 결과를 리턴받는 메소드입니다.
     */
    @Override
    public void onRequestPermissionsResult(int permsRequestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grandResults) {

        if ( permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length) {

            // 요청 코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면

            boolean check_result = true;


            // 모든 퍼미션을 허용했는지 체크합니다.

            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }


            if ( check_result ) {

                // 퍼미션을 허용했다면 위치 업데이트를 시작합니다.
                startLocationUpdates();
            }
            else {
                // 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료합니다.2 가지 경우가 있습니다.

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[1])) {


                    // 사용자가 거부만 선택한 경우에는 앱을 다시 실행하여 허용을 선택하면 앱을 사용할 수 있습니다.
                    Snackbar.make(mLayout, "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요. ",
                            Snackbar.LENGTH_INDEFINITE).setAction("확인", new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {

                            finish();
                        }
                    }).show();

                }else {


                    // "다시 묻지 않음"을 사용자가 체크하고 거부를 선택한 경우에는 설정(앱 정보)에서 퍼미션을 허용해야 앱을 사용할 수 있습니다.
                    Snackbar.make(mLayout, "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ",
                            Snackbar.LENGTH_INDEFINITE).setAction("확인", new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {

                            finish();
                        }
                    }).show();
                }
            }

        }
    }


    //여기부터는 GPS 활성화를 위한 메소드들
    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(mapActivity.this);
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 수정하실래요?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case GPS_ENABLE_REQUEST_CODE:

                //사용자가 GPS 활성 시켰는지 검사
                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()) {

                        Log.d(TAG, "onActivityResult : GPS 활성화 되있음");


                        needRequest = true;

                        return;
                    }
                }

                break;
        }
    }


    @Override
    public void onPlacesFailure(PlacesException e) {

    }

    @Override
    public void onPlacesStart() {

    }

    @Override
    public void onPlacesSuccess(final List<Place> places) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (noman.googleplaces.Place place : places) {

                    LatLng latLng
                            = new LatLng(place.getLatitude()
                            , place.getLongitude());

                    String markerSnippet = getCurrentAddress(latLng);

                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(latLng);
                    markerOptions.title(place.getName());
                    markerOptions.snippet(markerSnippet);
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                    Marker item = mGoogleMap.addMarker(markerOptions);
                    previous_marker.add(item);



                }

                /*//중복 마커 제거
                HashSet<Marker> hashSet = new HashSet<Marker>();
                hashSet.addAll(previous_marker);
                previous_marker.clear();
                previous_marker.addAll(hashSet);
*/
            }
        });

    }

    @Override
    public void onPlacesFinished() {

    }

    public void showPlaceInformation(LatLng location)
    {

     //   mGoogleMap.clear();//지도 클리어



        if (previous_marker != null)
            previous_marker.clear();//지역정보 마커 클리어

        new NRPlaces.Builder()
                .listener(mapActivity.this)
                .key("AIzaSyDf9KVO1G-IYdoOrBNYd5QCNcS5BLgCl2w")
                .latlng(location.latitude, location.longitude)//현재 위치
                .radius(3000) //500 미터 내에서 검색
                .type(PlaceType.RESTAURANT) //음식점
                .build()
                .execute();
    }

    public void ShowServiceRestorant_Korea() {


        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(new LatLng(37.214887, 126.978452))
                .title("황제갈비(Hwangjegalbi)")
                .snippet("Korea Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions1);

        MarkerOptions markerOptions2 = new MarkerOptions();
        markerOptions2.position(new LatLng(37.213580, 126.975345))
                .title("본죽 수원대점(Bonjuk)")
                .snippet("Korea Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions2);

        MarkerOptions markerOptions3 = new MarkerOptions();
        markerOptions3.position(new LatLng(37.214058, 126.977880))
                .title("파파이스 수원대점(Popeyes)")
                .snippet("Hamburger")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions3);

        MarkerOptions markerOptions4 = new MarkerOptions();
        markerOptions4.position(new LatLng(37.214887, 126.978452))
                .title("진고깃집(Jingogitjip)")
                .snippet("Korea Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions4);

        MarkerOptions markerOptions5 = new MarkerOptions();
        markerOptions5.position(new LatLng(37.209777, 126.981168))
                .title("예담한식부페(Yedamhansikbupe)")
                .snippet("Korea Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions5);

        MarkerOptions markerOptions6 = new MarkerOptions();
        markerOptions6.position(new LatLng(37.215764, 126.976647))
                .title("착한왕만두(Chakanwangmandu)")
                .snippet("Korea Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions6);

        MarkerOptions markerOptions7 = new MarkerOptions();
        markerOptions7.position(new LatLng(37.215574, 126.976476))
                .title("한판승부(Hanpanseungbu)")
                .snippet("Korea Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions7);

        MarkerOptions markerOptions8 = new MarkerOptions();
        markerOptions8.position(new LatLng(37.214040, 126.974053))
                .title("돈비가(Donbiga)")
                .snippet("Korea Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions8);

        MarkerOptions markerOptions9 = new MarkerOptions();
        markerOptions9.position(new LatLng(37.213916, 126.978159))
                .title("복돼지연탄구이(Bokdwaejiyeontangui)")
                .snippet("Korea Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions9);

        MarkerOptions markerOptions10 = new MarkerOptions();
        markerOptions10.position(new LatLng(37.214744, 126.978007))
                .title("1인자 24시간감자탕뼈해장국 와우점(Irinja)")
                .snippet("Korea Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions10);

        MarkerOptions markerOptions11 = new MarkerOptions();
        markerOptions11.position(new LatLng(37.214731, 126.977393))
                .title("제주도그릴(Jejudogeuril)")
                .snippet("Korea Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions11);

        MarkerOptions markerOptions12 = new MarkerOptions();
        markerOptions12.position(new LatLng(37.214168, 126.976052))
                .title("큰맘할매순대국(Keunmamhalmaesundaeguk)")
                .snippet("Korea Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions12);

        MarkerOptions markerOptions13 = new MarkerOptions();
        markerOptions13.position(new LatLng(37.214404, 126.978962))
                .title("내가찜한닭(Naegajjimhandak)")
                .snippet("Korea Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions13);

        MarkerOptions markerOptions14 = new MarkerOptions();
        markerOptions14.position(new LatLng(37.213740, 126.974806))
                .title("국수나무(Guksunamu)")
                .snippet("Korea Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions14);

        MarkerOptions markerOptions15 = new MarkerOptions();
        markerOptions15.position(new LatLng(37.214168, 126.976054))
                .title("찌개지존(Jjigaejijon)")
                .snippet("Korea Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions15);

        MarkerOptions markerOptions16 = new MarkerOptions();
        markerOptions16.position(new LatLng(37.214777, 126.976597))
                .title("등촌샤브칼국수(Deungchonsyabeukalguksu)")
                .snippet("Korea Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions16);

        MarkerOptions markerOptions17 = new MarkerOptions();
        markerOptions17.position(new LatLng(37.214395, 126.977924))
                .title("탄탄석쇠(Tantanseoksoe)")
                .snippet("Korea Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions17);

        MarkerOptions markerOptions18 = new MarkerOptions();
        markerOptions18.position(new LatLng(37.214681, 126.977057))
                .title("우가돈가(Ugadonga)")
                .snippet("Korea Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions18);

        MarkerOptions markerOptions19 = new MarkerOptions();
        markerOptions19.position(new LatLng(37.214192, 126.975897))
                .title("꽃소(Kkotso)")
                .snippet("Korea Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions19);


        MarkerOptions markerOptions20 = new MarkerOptions();
        markerOptions20.position(new LatLng(37.214681, 126.977057))
                .title("전주집(Jeonjujip)")
                .snippet("Korea Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions20);

        MarkerOptions markerOptions21 = new MarkerOptions();
        markerOptions21.position(new LatLng(37.215249, 126.977306))
                .title("와우순대국(Wausundaeguk)")
                .snippet("Korea Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions21);

        MarkerOptions markerOptions22 = new MarkerOptions();
        markerOptions22.position(new LatLng(37.214202, 126.975898))
                .title("여장군 수원대점(Yeojanggun)")
                .snippet("Korea Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions22);

        MarkerOptions markerOptions23 = new MarkerOptions();
        markerOptions23.position(new LatLng(37.214087, 126.978187))
                .title("행복한모임터(Haengbokanmoimteo)")
                .snippet("Korea Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions23);

        MarkerOptions markerOptions24 = new MarkerOptions();
        markerOptions24.position(new LatLng(37.214279, 126.976259))
                .title("차돌네 묵은지닭볶음탕(Chadolle)")
                .snippet("Korea Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions24);

        MarkerOptions markerOptions25 = new MarkerOptions();
        markerOptions25.position(new LatLng(37.214699, 126.977581))
                .title("정가네곱창전골(Jeonggane)")
                .snippet("Korea Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions25);

        MarkerOptions markerOptions26 = new MarkerOptions();
        markerOptions26.position(new LatLng(37.213814, 126.976198))
                .title("24시전주명가콩나물국밥(jeonjumyeonggakongnamulgukbap)")
                .snippet("Korea Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions26);

        MarkerOptions markerOptions27 = new MarkerOptions();
        markerOptions27.position(new LatLng(37.205835, 126.980529))
                .title("아르또맛집(Areuttomatjip)")
                .snippet("Korea Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions27);
    }
    public void ShowServiceRestorant_Chicken() {

        MarkerOptions markerOptions28 = new MarkerOptions();
        markerOptions28.position(new LatLng(37.214066, 126.975798))
                .title("bhc치킨 화성와우리점(bhc chicken)")
                .snippet("Chicken")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions28);

        MarkerOptions markerOptions29 = new MarkerOptions();
        markerOptions29.position(new LatLng(37.214337, 126.974953))
                .title("블랙꼬끄(black Kkokkeu)")
                .snippet("Chicken")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions29);

        MarkerOptions markerOptions30 = new MarkerOptions();
        markerOptions30.position(new LatLng(37.214393, 126.978962))
                .title("맘스터치 수원대점(Mom's Touch)")
                .snippet("Chicken")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions30);

        MarkerOptions markerOptions31 = new MarkerOptions();
        markerOptions31.position(new LatLng(37.214043, 126.979631))
                .title("짱닭치킨 화성수원대점(Jjangdak chicken)")
                .snippet("Chicken")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions31);

        MarkerOptions markerOptions32 = new MarkerOptions();
        markerOptions32.position(new LatLng(37.214010, 126.974052))
                .title("가마로강정 화성와우점(Gamarogangjeong)")
                .snippet("Chicken")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions32);

        MarkerOptions markerOptions33 = new MarkerOptions();
        markerOptions33.position(new LatLng(37.213386, 126.974157))
                .title("가마치통닭 수원대점(Gamachitongdak)")
                .snippet("Chicken")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions33);

        MarkerOptions markerOptions34 = new MarkerOptions();
        markerOptions34.position(new LatLng(37.214283, 126.976253))
                .title("또래오래 화성수원대점(Ttoraeorae)")
                .snippet("Chicken")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions34);

        MarkerOptions markerOptions35 = new MarkerOptions();
        markerOptions35.position(new LatLng(37.213558, 126.975645))
                .title("훌랄라참숯바베큐 와우1점(Hullallachamsut BBQ)")
                .snippet("Chicken")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions35);

        MarkerOptions markerOptions36 = new MarkerOptions();
        markerOptions36.position(new LatLng(37.213951, 126.974550))
                .title("치킨마루 와우리점(chicken Maru)")
                .snippet("Chicken")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions36);

        MarkerOptions markerOptions37 = new MarkerOptions();
        markerOptions37.position(new LatLng(37.215625, 126.976889))
                .title("도담치킨 수원대점(Dodam chicken)")
                .snippet("Chicken")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions37);

        MarkerOptions markerOptions38 = new MarkerOptions();
        markerOptions38.position(new LatLng(37.212820, 126.973819))
                .title("부어치킨 수원대점(Bueo chicken)")
                .snippet("Chicken")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions38);

        MarkerOptions markerOptions39 = new MarkerOptions();
        markerOptions39.position(new LatLng(37.215634, 126.976893))
                .title("티바두마리치킨 봉담점(Tibadumari chickn)")
                .snippet("Chicken")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions39);
    }

    public void ShowServiceRestorant_Itary() {

        MarkerOptions markerOptions52 = new MarkerOptions();
        markerOptions52.position(new LatLng(37.214286,126.974865))
                .title("파스타하임(pasta heim)")
                .snippet("Italy Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions52);
    }

    public void ShowServiceRestorant_FastFood(){

        MarkerOptions markerOptions62 = new MarkerOptions();
        markerOptions62.position(new LatLng(37.213558,126.975645))
                .title("59쌀피자(Ogussalpizza)")
                .snippet("Pizza")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions62);

        MarkerOptions markerOptions63 = new MarkerOptions();
        markerOptions63.position(new LatLng(37.215653,126.976878))
                .title("피자스쿨(pizza school)")
                .snippet("Pizza")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions63);

        MarkerOptions markerOptions64 = new MarkerOptions();
        markerOptions64.position(new LatLng(37.213682,126.974924))
                .title("버거킹 화성봉담점(burger king)")
                .snippet("HamBurger")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions64);

    }
    public void ShowServiceRestorant_NightFood(){

        MarkerOptions markerOptions43 = new MarkerOptions();
        markerOptions43.position(new LatLng(37.213586, 126.975342))
                .title("닭발왕(Dakbarwang)")
                .snippet("Night Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions43);

        MarkerOptions markerOptions45 = new MarkerOptions();
        markerOptions45.position(new LatLng(37.214555,126.974000))
                .title("사탄곱창(Satangopchang)")
                .snippet("Night Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions45);

        MarkerOptions markerOptions46 = new MarkerOptions();
        markerOptions46.position(new LatLng(37.210721,126.970367))
                .title("전설의곱창(Jeonseoruigopchang)")
                .snippet("Night Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions46);

        MarkerOptions markerOptions47 = new MarkerOptions();
        markerOptions47.position(new LatLng(37.214408,126.977881))
                .title("수원닭발(Suwondakbal)")
                .snippet("Night Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions47);

        MarkerOptions markerOptions48 = new MarkerOptions();
        markerOptions48.position(new LatLng(37.214624,126.977718))
                .title("와우곱창(Waugopchang)")
                .snippet("Night Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions48);

        MarkerOptions markerOptions49 = new MarkerOptions();
        markerOptions49.position(new LatLng(37.214129,126.976544))
                .title("가장맛있는족발(Gajangmasinneunjokbal)")
                .snippet("Night Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions49);

        MarkerOptions markerOptions50 = new MarkerOptions();
        markerOptions50.position(new LatLng(37.210725,126.970368))
                .title("깡패족발(Kkangpaejokbal)")
                .snippet("Night Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions50);

        MarkerOptions markerOptions51 = new MarkerOptions();
        markerOptions51.position(new LatLng(37.214183,126.977057))
                .title("화덕구이족보통(Hwadeokguijokbotong)")
                .snippet("Night Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions51);

    }
    public void ShowServiceRestorant_China(){

        MarkerOptions markerOptions53 = new MarkerOptions();
        markerOptions53.position(new LatLng(37.213823,126.976742))
                .title("홍콩반점0410 화성수원대점(Hongkongbanjeom)")
                .snippet("China Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions53);

        MarkerOptions markerOptions54 = new MarkerOptions();
        markerOptions54.position(new LatLng(37.2143568,126.979019))
                .title("챠이챠이 수원대점(Chyaichyai)")
                .snippet("China Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions54);

        MarkerOptions markerOptions55 = new MarkerOptions();
        markerOptions55.position(new LatLng(37.215203,126.978533))
                .title("청마루중국집(Cheongmaru)")
                .snippet("China Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions55);

        MarkerOptions markerOptions56 = new MarkerOptions();
        markerOptions56.position(new LatLng(37.214294,126.976261))
                .title("홍보석(Hongboseok)")
                .snippet("China Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions56);

        MarkerOptions markerOptions57 = new MarkerOptions();
        markerOptions57.position(new LatLng(37.215177,126.976492))
                .title("진쿵푸마라탕(jonKungfuMaratang)")
                .snippet("China Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions57);

        MarkerOptions markerOptions58 = new MarkerOptions();
        markerOptions58.position(new LatLng(37.214769,126.972514))
                .title("로충칭마라탕 화성봉담점(Rongchungchingmaratang)")
                .snippet("China Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions58);

        MarkerOptions markerOptions59 = new MarkerOptions();
        markerOptions59.position(new LatLng(37.215201,126.977595))
                .title("하오츠양꼬치(Haocheuyangkkochi)")
                .snippet("China Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions59);

        MarkerOptions markerOptions60 = new MarkerOptions();
        markerOptions60.position(new LatLng(37.214923,126.978377))
                .title("취향저격안심탕수육(Chwihyangjeogyeogansimtangsuyuk)")
                .snippet("China Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions60);

        MarkerOptions markerOptions61 = new MarkerOptions();
        markerOptions61.position(new LatLng(37.216430,126.975521))
                .title("중국전통삼겸찝 동파육(tongpayuk)")
                .snippet("China Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions61);

    }

        public void ShowServiceRestorant_Japan() {
        MarkerOptions markerOptions40 = new MarkerOptions();
        markerOptions40.position(new LatLng(37.214025, 126.974056))
                .title("최고당돈가스 수원대점(Choegodang dongaseu)")
                .snippet("Japan Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions40);

        MarkerOptions markerOptions41 = new MarkerOptions();
        markerOptions41.position(new LatLng(37.213994, 126.979792))
                .title("산쪼메 수원대점(Sanjjome)")
                .snippet("Japan Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions41);

        MarkerOptions markerOptions42 = new MarkerOptions();
        markerOptions42.position(new LatLng(37.215192, 126.978532))
                .title("이찌돈 와우점(Ijjidon)")
                .snippet("Japan Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions42);

        MarkerOptions markerOptions44 = new MarkerOptions();
        markerOptions44.position(new LatLng(37.214194, 126.975895))
                .title("참치사랑(Chamchisarang)")
                .snippet("Japan Food")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions44);
    }


}