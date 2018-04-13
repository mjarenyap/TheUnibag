package security;
import java.time.LocalDateTime;

public class Expiration{
	public Expiration(){}
	/*
	public static boolean isExpired(LocalDateTime lastLogged){
		if(lastLogged != null){
			int expireQuota = 30; // in minutes
			LocalDateTime now = LocalDateTime.now();

			int hours = now.getHour() - lastLogged.getHour();
			int minutes = now.getMinute() - lastLogged.getMinute();
			int seconds = now.getSecond() - lastLogged.getSecond();

			if(seconds < 0)
				seconds += 60;

			else if(seconds >= 59 && minutes > 0)
				minutes++;


			if(minutes < 0)
				minutes += 60;

			else if(minutes >= 59 && hours > 0)
				hours++;


			if(hours != 0)
				return true;

			else if(hours == 0 && minutes >= expireQuota)
				return true;
		}

		return false;
	}
	*/

	public static boolean isExpired(LocalDateTime lastLogged){
		if(lastLogged != null){
			int expireQuota = 1; // in minutes
			LocalDateTime now = LocalDateTime.now();

			lastLogged = lastLogged.plusSeconds((long)(expireQuota*60));
			if(lastLogged.compareTo(now) <= 0)
				return true;
		}

		return false;
	}
}