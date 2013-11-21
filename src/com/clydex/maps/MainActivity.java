package com.clydex.maps;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements LocationListener {

	private GoogleMap mMap;
	private Location myLocation;
	private LatLng latLng;

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
		 myLocation = locationManager.getLastKnownLocation(provider);

		// set map type
		mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

		// Get latitude of the current location
		double latitude = myLocation.getLatitude();

		// Get longitude of the current location
		double longitude = myLocation.getLongitude();

		// Create a LatLng object for the current location
		latLng = new LatLng(latitude, longitude);

		// Show the current location in Google Map
		mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

		// Zoom in the Google Map
		mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
		mMap.addMarker(new MarkerOptions()
				.position(new LatLng(latitude, longitude))
				.title("You are here!")
				.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

		// Zoom in the Tyfanny's Map
		Marker Marker2 = mMap.addMarker(new MarkerOptions().position(
				new LatLng(8.18706, 126.35402)).title("Tyfanny's Restaurant"));
		Marker2.getId();

		mMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {

			@Override
			public void onInfoWindowClick(Marker marker) {

				Toast.makeText(getBaseContext(), "Click Info Window",
						Toast.LENGTH_SHORT).show();
			}

		});

	}

	@Override
	protected void onResume() {
		super.onResume();
		setUpMapIfNeeded();

	}

	@Override
	public void onLocationChanged(Location location) {
		// Get latitude of the current location
				double latitude = myLocation.getLatitude();

				// Get longitude of the current location
				double longitude = myLocation.getLongitude();

				// Create a LatLng object for the current location
				latLng = new LatLng(latitude, longitude);

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	private void setUpMapIfNeeded() {
		// Do a null check to confirm that we have not already instantiated the
		// map.
		if (mMap == null) {
			// Try to obtain the map from the SupportMapFragment.
			mMap = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map)).getMap();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
