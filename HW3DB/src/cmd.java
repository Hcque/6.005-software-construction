import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class cmd {

	public static void main(String[] args) throws IOException {
		DBTable db = new DBTable();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line;

		while (true) {
			System.out.format("\n%d records (%d selected)\n", db.getNumOfRecords(), db.countSelected());
			System.out.println("r read, p print, sa select and, so select or, da ds du delete, c clear sel ");
			System.out.print("db:");
			line = reader.readLine();
			//System.out.println(line);
			switch (line) {
			case "r":
				System.out.println("read");
				System.out.print("Filename:");
				db.addData(reader.readLine());
				break;
			case "p":
				System.out.println("print");
				System.out.println(db);
				break;
			case "sa":
				System.out.println("select and");
				System.out.print("Criteria record:");
				db.selectAnd(reader.readLine());
				break;
			case "so":
				System.out.println("select or");
				System.out.print("Criteria record:");
				db.selectOr(reader.readLine());
				break;
			case "da":
				System.out.println("delete all");
				db.deleteAll();
				break;
			case "ds":
				System.out.println("delete selected");
				db.deleteSelected();
				break;
			case "du":
				System.out.println("delete unselected");
				db.deleteUnselected();
				break;
			case "c":
				System.out.println("clear selection");
				db.clearSelection();
				break;
			case "quit":
				System.exit(0);

			default:
				break;
			}
		}
	}

}
