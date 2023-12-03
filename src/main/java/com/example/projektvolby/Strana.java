package com.example.projektvolby;

import javafx.scene.text.Text;

public class Strana {
	private long id;
	private String nazov;
	private Text volebny_plan;
	private long pocet_hlasov;


	public Strana(long id, String nazov, Text volebny_plan, long pocet_hlasov) {
		this.id = id;
		this.nazov = nazov;
		this.volebny_plan = volebny_plan;
		this.pocet_hlasov = pocet_hlasov;
	}


}
