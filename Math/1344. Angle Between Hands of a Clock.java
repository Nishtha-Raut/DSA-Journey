//leetcode link: https://leetcode.com/problems/angle-between-hands-of-a-clock/?envType=daily-question&envId=2026-06-18
class Solution {
    public double angleClock(int hour, int minutes) {
        double minAngle=minutes*6;
        double hourAngle=(hour%12)*30.0+((double)minutes/60.0)*30;
        double diff=Math.abs(minAngle-hourAngle);
        return Math.min(diff,360.0-diff);
    }
}
"""
  This code finds the smallest angle between the hour and minute hands on a clock.
  -> minutes * 6: The minute hand moves 6 degree every minute (360 degree / 6o degree minutes).
  -> (hour % 12) * 30.0: The hour hand moves 30 degree every hour ((360 degree /12) hours). 
      hour % 12 converts 12 o'clock to 0.
  -> ((double)minutes / 60.0) * 30: The hour hand also moves a little bit based on the extra minutes passed.
  -> Math.abs(...): Calculates the absolute difference between both angles.
  -> Math.min(diff, 360.0 - diff): Returns the smaller angle because a circle has two paths between the hands (inner and outer).
"""
