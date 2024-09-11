CREATE TABLE resume
(
uuid char(64) NOT NULL PRIMARY KEY,
full_name TEXT NOT NULL
);

CREATE TABLE contact (
  id          SERIAL(64),
  resume_uuid CHAR(36) NOT NULL REFERENCES resume (uuid) ON DELETE CASCADE,
  type        TEXT     NOT NULL,
  value       TEXT     NOT NULL
);


CREATE UNIQUE INDEX contact_uuid_type_index
  ON contact (resume_uuid, type);

select * FROM resume;

  INSERT INTO resume (uuid, full_name)
  VALUES ('7de882da-02f2-4d16-8daa-60660aaf4071', 'Name1');
  INSERT INTO resume (uuid, full_name)
  VALUES ('a97b3ac3-3817-4c3f-8a5f-178497311f1d', 'Name2');
  INSERT INTO resume (uuid, full_name)
  VALUES ('dd0a70d1-5ed3-479a-b452-d5e04f21ca73', 'Name3');
  select * FROM resume;

  INSERT INTO resume (uuid, full_name)
  VALUES ('0000000', 'Name4');
  select * FROM resume;