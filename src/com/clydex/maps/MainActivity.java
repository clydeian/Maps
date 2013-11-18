package com.clydex.maps;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class MainActivity extends FragmentActivity {

	private GoogleMap mMap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 setUpMapIfNeeded();
		 
		 // Enabling MyLocation Layer of Google Map
	        mMap.setMyLocationEnabled(true);
	        // Get LocationManager object from System Service LOCATION_SERVICE
	        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

	        // Create a criteria object to retrieve provider
	        Criteria criteria = new Criteria();

	        // Get the name of the best provider
	        String provider = locationManager.getBestProvider(criteria, true);

	        // Get Current Location
	        Location myLocation = locationManager.getLastKnownLocation(provider);

	        //set map type
	        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

	        // Get latitude of the current location
	        double latitude = myLocation.getLatitude();

	        // Get longitude of the current location
	        double longitude = myLocation.getLongitude();

	        // Create a LatLng object for the current location
	        LatLng latLng = new LatLng(latitude, longitude);      

	        // Show the current location in Google Map        
	        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

	        // Zoom in the Google Map
	        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
	        mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("You are here!"));    
	        
	}
	
	 @Override
	    protected void onResume() {
	        super.onResume();
	        setUpMapIfNeeded();
	    }
	 
	 private void setUpMapIfNeeded() {
	        // Do a null check to confirm that we have not already instantiated the map.
	        if (mMap == null) {
	            // Try to obtain the map from the SupportMapFragment.
	            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
	                    .getMap();
	        }
	    }
	 
	 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
