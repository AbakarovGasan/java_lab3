import java.util.*;
/**
 * This class stores the basic state necessary for the A* algorithm to compute a
 * path across a map.  This state includes a collection of "open waypoints" and
 * another collection of "closed waypoints."  In addition, this class provides
 * the basic operations that the A* pathfinding algorithm needs to perform its
 * processing.
 **/
public class AStarState
{
    /** This is a reference to the map that the A* algorithm is navigating. **/
    private Map2D map;


    /** Открытый набор вершин. **/
    private Map<Location, Waypoint> open_waypoints
    = new HashMap<Location, Waypoint> ();
    
    /** Закрытый набор вершин. **/
    private Map<Location, Waypoint> closed_waypoints
    = new HashMap<Location, Waypoint> ();

    /**
     * Initialize a new state object for the A* pathfinding algorithm to use.
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    };

    /** Returns the map that the A* pathfinder is navigating. **/
    public Map2D getMap()
    {
        return map;
    };
    
    public int numOpenWaypoints()
    {
        //возвращает количество открытых вершин
        return open_waypoints.size();
    };

    public Waypoint getMinOpenWaypoint()
    {
        // если нет открытых вершин, возвращает null.
        if (open_waypoints.size()==0) return null;
        // объявить итератор для ключей от open_waypoints
        Collection o = open_waypoints.values();
        Iterator i = o.iterator();
        
        Waypoint best = (Waypoint) i.next();
        float best_cost = best.getTotalCost();
        // проверить, есть ли вершина, стоимость которой ниже вершины best
        while (i.hasNext())
        {
            Waypoint waypoint = (Waypoint) i.next();
            float waypoint_cost = waypoint.getTotalCost();
            // если стоимость вершины waypoint ниже вершины best
            if (waypoint_cost < best_cost)
            {
                best = waypoint;
                best_cost = waypoint_cost;
            }
        }
        //возвращает ссылку на вершину с наименьшей стоимостью
        return best;
    }

    public boolean addOpenWaypoint(Waypoint newWP)
    {
        // получить переменную location из newWP.
        Location location = newWP.getLocation();
        
        // Проверяет, есть ли уже location в open_waypoints.
        if (open_waypoints.containsKey(location))
        {
            // если да, то получить waypoint и сравнить, чья стоимость ниже
            if (newWP.getPreviousCost() > open_waypoints.get(location).getPreviousCost())
            {     
                //если стоимоть newWP выше, то завершить
                return false;
            }
        }
        // добавить newWP в open_waypoints
        open_waypoints.put(location, newWP);
        return true;
    }

    public boolean isLocationClosed(Location loc)
    {
        // возвращает true, если loc содержится в наборе closed_waypoints
        return closed_waypoints.containsKey(loc);
    }
    
    public void closeWaypoint(Location loc)
    {
        // переместить вершину из open_waypoints в сlosed_waypoints
        closed_waypoints.put(loc, open_waypoints.remove(loc));
    }
}

