package ec.edu.espe.examen3.config;

public class Configuraciones {
    public enum TIPO_TRANSACCION {
		DEPOSITO("DEP"), RETIRO("RET");

		private final String tipo;

		private TIPO_TRANSACCION(String tipo) {
			this.tipo = tipo;
		}

		public String getTipo() {
			return tipo;
		}

	}
}
