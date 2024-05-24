#!/bin/sh

# Run database migrations
python -c "from database import Base, engine; Base.metadata.create_all(bind=engine)"

# Start the application
exec uvicorn main:app --host 0.0.0.0 --port 80