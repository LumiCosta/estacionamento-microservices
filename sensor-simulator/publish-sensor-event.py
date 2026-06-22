import pika, json, sys
vaga_id = int(sys.argv[1]) if len(sys.argv) > 1 else 1
ocupada = (sys.argv[2].lower() == 'true') if len(sys.argv) > 2 else True
conn = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
ch = conn.channel()
ch.exchange_declare(exchange='parking.events', exchange_type='direct', durable=True)
evento = {'vagaId': vaga_id, 'ocupada': ocupada}
ch.basic_publish(exchange='parking.events', routing_key='sensor.vaga', body=json.dumps(evento), properties=pika.BasicProperties(content_type='application/json'))
print('Evento enviado:', evento)
conn.close()
