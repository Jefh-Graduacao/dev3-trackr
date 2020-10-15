export default interface Entrega {
  movimentacoes: Movimentacao[]
}

export interface Movimentacao {
  dataHora: Date,
  titulo: string,
  situacao: string
}
