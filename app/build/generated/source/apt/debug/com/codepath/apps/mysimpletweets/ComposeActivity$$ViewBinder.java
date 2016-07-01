// Generated code from Butter Knife. Do not modify!
package com.codepath.apps.mysimpletweets;

import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class ComposeActivity$$ViewBinder<T extends ComposeActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends ComposeActivity> implements Unbinder {
    protected T target;

    protected InnerUnbinder(T target, Finder finder, Object source) {
      this.target = target;

      target.etCompose = finder.findRequiredViewAsType(source, 2131427435, "field 'etCompose'", EditText.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.etCompose = null;

      this.target = null;
    }
  }
}
