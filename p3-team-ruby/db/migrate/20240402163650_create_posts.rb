class CreatePosts < ActiveRecord::Migration[7.1]
  def change
    create_table :posts do |t|
      t.text :body
      t.integer :likes

      t.timestamps
    end
  end
end
