package utils
{
	import flash.geom.Point;

	public class Utils
	{
		public function Utils()
		{
		}
		
//		public static function rotate_point(center_x:Number,center_y:Number, radio:Number, angle:Number):Point
//		{
//			var point:Point = new Point;
//			var radian:Number = deg_to_rad(angle);
//			
//			point.x = center_x - (radio* (Math.sin(radian)));
//			point.y = center_y + (radio* (Math.cos(radian)));
//				
//			return point;
//			
//		}
		
		public static function point_distance(p1:Point,p2:Point):Number
		{
			var dist: Number;
			var dx:Number;
			var dy:Number;
			
			dx = p2.x - p1.x;
			dy = p2.y - p1.y;
			
			dist = Math.sqrt(dx*dx + dy*dy);
			
			return dist;
		}
		
		/**
		 * returns radians	
		 */
		public static function point_direction(p1:Point,p2:Point):Number
		{
			var dx:Number;
			var dy:Number;
			
			dx = p2.x - p1.x;
			dy = p2.y - p1.y;
			
			var angle:Number = Math.atan2(dy,dx);
			
			return angle;
		}
		
		public static function deg_to_rad(deg:Number):Number
		{
			var radian:Number = deg * Math.PI / 180;
			
			return radian;
		}
		
		public static function rad_to_deg(rad:Number):Number
		{
			var deg:Number = rad * 180/Math.PI;
			
			return deg;
		}
	}
}