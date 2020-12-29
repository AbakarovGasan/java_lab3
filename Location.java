/**
 * This class represents a specific location in a 2D map.  Coordinates are
 * integer values.
 **/
public class Location
{
    
    // проверяет, равны ли локации
    public boolean equals(Location loc){
        // возвращает true, если координаты равны
        return ((loc.xCoord==this.xCoord)&&(loc.yCoord==this.yCoord));
    }
    

    public boolean equals(Object obj){
            // проверяет, является ли объект локацией
            // если да, возвращает, равны ли локации
        if (obj instanceof Location) return equals( (Location) obj); 
        else return false;
    }
    
    // генерирует хеш код
    public int hashCode(){
        int a = this.xCoord;
        int b = this.yCoord;
        int t0 = a+b;
        int result = 0;
        // шифрование методом хеширования с применением гаммирования
        // гаммирование - это наложение одной последовательности на другую
        int t1=(a*t0+b)%65536;
        int t2=(a*t1+b)%65536;
        result += this.xCoord ^ t1; 
        result += this.yCoord ^ t2;
        return result;
    }
        
    /** X coordinate of this location. **/
    public int xCoord;

    /** Y coordinate of this location. **/
    public int yCoord;
    
    

    /** Creates a new location with the specified integer coordinates. **/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /** Creates a new location with coordinates (0, 0). **/
    public Location()
    {
        this(0, 0);
    }
}
