package boardEx;

public class MapLayoutToken {
   private MapLayout[] mapLayoutArr;
   
   public MapLayoutToken() {
      super();
      mapLayoutArr = new MapLayout[28];
      
      mapLayoutArr [0] = new MapLayout (744,692+32);
      mapLayoutArr [1] = new MapLayout(640,692+32);
      mapLayoutArr [2] = new MapLayout (536,692+32);
      mapLayoutArr [3] = new MapLayout (432,692+32);
      mapLayoutArr [4] = new MapLayout (328,692+32);
      mapLayoutArr [5] = new MapLayout (224,692+32);
      mapLayoutArr [6] = new MapLayout (120,692+32);
      mapLayoutArr [7] = new MapLayout (16,692+32);
      mapLayoutArr [8] = new MapLayout (16,596+32);
      mapLayoutArr [9] = new MapLayout (16,500+32);
      mapLayoutArr [10] = new MapLayout (16,404+32);
      mapLayoutArr [11] = new MapLayout (16,308+32);
      mapLayoutArr [12] = new MapLayout (16,212+32);
      mapLayoutArr [13] = new MapLayout (16,116+32);
      mapLayoutArr [14] = new MapLayout (16,20+32);
      mapLayoutArr [15] = new MapLayout (120,20+32);
      mapLayoutArr [16] = new MapLayout (224,20+32);
      mapLayoutArr [17] = new MapLayout (328,20+32);
      mapLayoutArr [18] = new MapLayout (432,20+32);
      mapLayoutArr [19] = new MapLayout (536,20+32);
      mapLayoutArr [20] = new MapLayout (640,20+32);
      mapLayoutArr [21] = new MapLayout (744,20+32);
      mapLayoutArr [22] = new MapLayout (744,116+32);
      mapLayoutArr [23] = new MapLayout (744,212+32);
      mapLayoutArr [24] = new MapLayout (744,308+32);
      mapLayoutArr [25] = new MapLayout (744,404+32);
      mapLayoutArr [26] = new MapLayout (744,500+32);
      mapLayoutArr [27] = new MapLayout (744,596+32);
      
      
   }
   
   public MapLayout[] getmapLayoutArr() {
      return mapLayoutArr;
      
   }

}