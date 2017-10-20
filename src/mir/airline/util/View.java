package mir.airline.util;

public enum View {
	
	pilot{

		@Override
		public String toString() {
			return "WEB-INF/view/pilot";
		}
		
	},
	
	view{

		@Override
		public String toString() {
			return "WEB-INF/view";
		}
		
	}

}
