// Generated code from Butter Knife. Do not modify!
package com.codepath.apps.mysimpletweets;

import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class TweetsAdapter$ViewHolder$$ViewBinder<T extends TweetsAdapter.ViewHolder> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends TweetsAdapter.ViewHolder> implements Unbinder {
    protected T target;

    protected InnerUnbinder(T target, Finder finder, Object source) {
      this.target = target;

      target.ivProfileImage = finder.findRequiredViewAsType(source, 2131427442, "field 'ivProfileImage'", ImageView.class);
      target.tvUserName = finder.findRequiredViewAsType(source, 2131427459, "field 'tvUserName'", TextView.class);
      target.tvBody = finder.findRequiredViewAsType(source, 2131427460, "field 'tvBody'", TextView.class);
      target.tvTimestamp = finder.findRequiredViewAsType(source, 2131427461, "field 'tvTimestamp'", TextView.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.ivProfileImage = null;
      target.tvUserName = null;
      target.tvBody = null;
      target.tvTimestamp = null;

      this.target = null;
    }
  }
}
