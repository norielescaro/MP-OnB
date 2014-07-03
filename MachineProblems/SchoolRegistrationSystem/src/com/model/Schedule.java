package com.model;

public class Schedule {
  private Day day;
  private Period period;
  
  public Schedule(Day day, Period period) {
    this.day = day;
    this.period = period;
  }

  public void setDay(Day day) {
    this.day = day;
  }

  public Day getDay() {
    return day;
  }

  public void setPeriod(Period period) {
    this.period = period;
  }

  public Period getPeriod() {
    return period;
  }


  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof Schedule)) {
      return false;
    }
    final Schedule other = (Schedule)object;
    if (!(day == null ? other.day == null : day.equals(other.day))) {
      return false;
    }
    if (!(period == null ? other.period == null : period.equals(other.period))) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    final int PRIME = 37;
    int result = 1;
    result = PRIME * result + ((day == null) ? 0 : day.hashCode());
    result = PRIME * result + ((period == null) ? 0 : period.hashCode());
    return result;
  }
}
