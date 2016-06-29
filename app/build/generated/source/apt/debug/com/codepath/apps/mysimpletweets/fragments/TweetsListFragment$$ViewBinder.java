// Generated code from Butter Knife. Do not modify!
package com.codepath.apps.mysimpletweets.fragments;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class TweetsListFragment$$ViewBinder<T extends TweetsListFragment> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends TweetsListFragment> implements Unbinder {
    protected T target;

    protected InnerUnbinder(T target, Finder finder, Object source) {
      this.target = target;

      target.rvTweets = finder.findRequiredViewAsType(source, 2131427458, "field 'rvTweets'", RecyclerView.class);
      target.swipeContainer = finder.findRequiredViewAsType(source, 2131427457, "field 'swipeContainer'", SwipeRefreshLayout.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.rvTweets = null;
      target.swipeContainer = null;

      this.target = null;
    }
  }
}
