package android.accessguarded.guarded365.co.uk.guardedguard.guardreview;

import android.accessguarded.guarded365.co.uk.guardedguard.R;
import android.accessguarded.guarded365.co.uk.guardedguard.guards.Guard;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

public class ReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        removeActionBarShadow();

        Guard guard = (Guard) getIntent().getSerializableExtra("guard");
        populateDetails(guard);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Display animation when returning to the home screen
            supportFinishAfterTransition();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void populateDetails(Guard guard) {
        // Init views
        final TextView initialsTextView = (TextView) findViewById(R.id.initialsTextView);
        final ImageView photoImageView = (ImageView) findViewById(R.id.photoImageView);
        final TextView nameTextView = (TextView) findViewById(R.id.nameTextView);
        final TextView tasksTextView = (TextView) findViewById(R.id.tasksTextView);

        // Display data
        initialsTextView.setText(guard.getInitials());
        nameTextView.setText(guard.getFullName());
        tasksTextView.setText(guard.getTaskCount(this));
        Glide.with(this).load(guard.getPhotoUrl()).asBitmap().centerCrop().into(new BitmapImageViewTarget(photoImageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                photoImageView.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    private void removeActionBarShadow() {
        getSupportActionBar().setElevation(0);
    }
}